package com.example.accuweather_2_0.dao;

import android.content.Context;
import android.location.Location;
import android.os.Looper;

import androidx.core.app.ActivityCompat;

import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.SharedPreferencesManager;
import com.example.accuweather_2_0.MainActivityContract;
import com.example.accuweather_2_0.model.screen.DetailsScreenModel;
import com.example.accuweather_2_0.model.screen.MainScreenModel;
import com.example.accuweather_2_0.model.WeatherParamCardModel;
import com.example.accuweather_2_0.model.weather.City;
import com.example.accuweather_2_0.model.weather.DailyForecast;
import com.example.accuweather_2_0.model.weather.HourlyForecast;
import com.example.accuweather_2_0.model.weather.Weather;
import com.example.accuweather_2_0.network.RetrofitClient;
import com.example.accuweather_2_0.network.WeatherReuest;
import com.example.accuweather_2_0.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.example.accuweather_2_0.model.enams.AirAndPollenNames.AIR_QUALITY;

public class WeatherRepositoryImpl implements WeatherRepository {
    private List<HourlyForecast> hourlyForecasts;
    private List<DailyForecast> dailyForecasts;
    private City city;
    private final WeatherReuest weatherReuest;
    private final MainActivityContract.Presenter presenter;
    private final Context context;
    private Location location;
    private final static String RESPONSE_CODE_KEY = "Code";
    //location
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;


    public WeatherRepositoryImpl(MainActivityContract.Presenter presenter) {
        weatherReuest = RetrofitClient.getInstance().create(WeatherReuest.class);
        this.presenter = presenter;
        context = presenter.getContext();
    }

    @Override
    public void updateLocationAndWeatherData() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        createLocationCallback();
        createLocationRequest();
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            presenter.requestPermissions();
        } else {
            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback, Looper.myLooper());
        }
    }

    @Override
    public void updateWeatherData() {
        city = SharedPreferencesManager.getInstance().getCity();
        if (city != null) {
            presenter.showNetworkProgressDialog();
            updateDailyForecasts();
            updateHourlyForecasts();
        }
    }

    @Override
    public void responseToPresenter() {
        if (dailyForecasts != null && hourlyForecasts != null) {
            MainScreenModel mainScreenModel = new MainScreenModel(
                    city.localizedName,
                    String.valueOf(Math.round(hourlyForecasts.get(0).getTemperature().getValue())),
                    hourlyForecasts.get(0).getIconPhrase(),
                    Utils.getAirAndPollenValue(AIR_QUALITY.getName(), dailyForecasts.get(0).getAirAndPollen()),
                    dailyForecasts
            );

            List<WeatherParamCardModel> cardModels = new ArrayList<>();
            cardModels.add(new WeatherParamCardModel(context.getString(R.string.felt),
                    Math.round(hourlyForecasts.get(0).getRealFeelTemperature().getValue()) + context.getString(R.string.celsius_degree)));
            cardModels.add(new WeatherParamCardModel(context.getString(R.string.humidity),
                    context.getString(R.string.humidity_value)));
            cardModels.add(new WeatherParamCardModel(context.getString(R.string.chance_of_rain),
                    hourlyForecasts.get(0).getRainProbability() + context.getString(R.string.percent)));
            cardModels.add(new WeatherParamCardModel(context.getString(R.string.pressure),
                    hourlyForecasts.get(0).getCeiling().getValue() + context.getString(R.string.pressure_value)));
            cardModels.add(new WeatherParamCardModel(context.getString(R.string.wind_speed),
                    hourlyForecasts.get(0).getWind().getSpeed().getValue()
                            + context.getString(R.string.wind_speed_measuring)));
            cardModels.add(new WeatherParamCardModel(context.getString(R.string.UV_index),
                    String.valueOf(hourlyForecasts.get(0).getuVIndex())));
            DetailsScreenModel detailsScreenModel = new DetailsScreenModel(
                    cardModels,
                    hourlyForecasts,
                    dailyForecasts.get(0).getSun().getRise(),
                    dailyForecasts.get(0).getSun().getSet(),
                    Utils.getAirAndPollenValue(AIR_QUALITY.getName(), dailyForecasts.get(0).getAirAndPollen())
            );

            SharedPreferencesManager.getInstance().putMainScreenModel(mainScreenModel);
            SharedPreferencesManager.getInstance().putDetailsScreenModel(detailsScreenModel);
            presenter.onDataUpdateSuccessful(mainScreenModel, detailsScreenModel);
        }
    }

    @Override
    public void stopUpdatesLocation() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void updateCity() {
        presenter.showNetworkProgressDialog();
        Call<City> call = weatherReuest.getCity(Utils.getLocationString(location));
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(@NotNull Call<City> call, @NotNull Response<City> response) {
                if (response.isSuccessful() && response.body() != null) {
                    city = response.body();
                    SharedPreferencesManager.getInstance().putCity(city);
                    updateDailyForecasts();
                    updateHourlyForecasts();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        presenter.onDataUpdateFailure(jsonObject.getString(RESPONSE_CODE_KEY));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<City> call, @NotNull Throwable t) {
                presenter.onDataUpdateFailure(t.getLocalizedMessage());
            }
        });
    }

    private void updateDailyForecasts() {
        Call<Weather> forecastsCall =
                weatherReuest.getDailyForecasts(city.getKey(), true, true);
        forecastsCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dailyForecasts = response.body().getDailyForecasts();
                    responseToPresenter();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        presenter.onDataUpdateFailure(jsonObject.getString(RESPONSE_CODE_KEY));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Weather> call, @NotNull Throwable t) {
                presenter.onDataUpdateFailure(t.getLocalizedMessage());
            }
        });
    }

    private void updateHourlyForecasts() {
        Call<List<HourlyForecast>> forecastCall =
                weatherReuest.getHourlyForecasts(city.getKey(), true, true);
        forecastCall.enqueue(new Callback<List<HourlyForecast>>() {
            @Override
            public void onResponse(@NotNull Call<List<HourlyForecast>> call, @NotNull Response<List<HourlyForecast>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hourlyForecasts = response.body();
                    responseToPresenter();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        presenter.onDataUpdateFailure(jsonObject.getString(RESPONSE_CODE_KEY));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<HourlyForecast>> call, @NotNull Throwable t) {
                presenter.onDataUpdateFailure(t.getLocalizedMessage());
            }
        });
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NotNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                location = locationResult.getLastLocation();
                updateCity();
                fusedLocationClient.removeLocationUpdates(locationCallback);
                presenter.cancelLocationUpdateDialog();
            }
        };
    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
}
