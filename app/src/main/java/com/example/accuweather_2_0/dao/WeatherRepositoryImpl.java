package com.example.accuweather_2_0.dao;

import android.util.Log;

import com.example.accuweather_2_0.model.MainScreenModel;
import com.example.accuweather_2_0.model.weather.City;
import com.example.accuweather_2_0.model.weather.DailyForecast;
import com.example.accuweather_2_0.model.weather.HourlyForecast;
import com.example.accuweather_2_0.model.weather.Weather;
import com.example.accuweather_2_0.network.RetrofitClient;
import com.example.accuweather_2_0.network.WeatherReuest;
import com.example.accuweather_2_0.presenter.MainActivityPresenter;
import com.example.accuweather_2_0.utils.Utils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.accuweather_2_0.App.currentLocation;
import static com.example.accuweather_2_0.network.RetrofitClient.API_KEY;
import static com.example.accuweather_2_0.network.RetrofitClient.LANGUAGE_RU;

public class WeatherRepositoryImpl implements WeatherRepository {
    private List<HourlyForecast> hourlyForecasts;
    private List<DailyForecast> dailyForecasts;
    private String cityLocationKey;
    private final WeatherReuest weatherReuest;
    private MainActivityPresenter presenter;

    public WeatherRepositoryImpl() {
        weatherReuest = RetrofitClient.getInstance().create(WeatherReuest.class);
    }

    @Override
    public void requestDataFromServer(MainActivityPresenter presenter) {
        if (currentLocation == null) {
            Log.i("dev", "Main Repo location == null");
            presenter.requestPermissions();
        }
        this.presenter = presenter;

        Call<City> call = weatherReuest.getCity(API_KEY, Utils.getLocationString());
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(@NotNull Call<City> call, @NotNull Response<City> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        presenter.onDataUpdateFailure(jsonObject.getString("Code"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (response.isSuccessful() && response.body() != null) {
                    cityLocationKey = response.body().getKey();
                    Log.i("dev", "onResponse city cityLocationKey " + cityLocationKey);
                    updateDailyForecasts();
                    updateHourlyForecasts();
                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                presenter.onDataUpdateFailure(t.getLocalizedMessage());
            }
        });
    }

    private void updateDailyForecasts() {
        Call<Weather> forecastsCall =
                weatherReuest.getDailyForecasts(cityLocationKey, API_KEY, LANGUAGE_RU, true, true);
        forecastsCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        presenter.onDataUpdateFailure(jsonObject.getString("Code"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (response.isSuccessful() && response.body() != null) {
                    dailyForecasts = response.body().getDailyForecasts();
                    updatePresenter();
                    Log.i("dev", "updateDayForecasts() " + dailyForecasts.get(0).toString());
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
                weatherReuest.getHourlyForecasts(cityLocationKey, API_KEY, LANGUAGE_RU, true, true);
        forecastCall.enqueue(new Callback<List<HourlyForecast>>() {
            @Override
            public void onResponse(@NotNull Call<List<HourlyForecast>> call, @NotNull Response<List<HourlyForecast>> response) {
                if (!response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        presenter.onDataUpdateFailure(jsonObject.getString("Code"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (response.isSuccessful() && response.body() != null) {
                    hourlyForecasts = response.body();
                    updatePresenter();
                    Log.i("dev", "updateHourlyForecasts() " + hourlyForecasts.get(0).toString());
                }
            }

            @Override
            public void onFailure(Call<List<HourlyForecast>> call, Throwable t) {
                presenter.onDataUpdateFailure(t.getLocalizedMessage());
            }
        });
    }

    private void updatePresenter() {
        if (dailyForecasts != null && hourlyForecasts != null) {
            Log.i("dev", " updatePresenter() " + hourlyForecasts.get(0).toString());
            MainScreenModel mainScreenModel = new MainScreenModel(
                    String.valueOf(Math.round(hourlyForecasts.get(0).getTemperature().getValue())),
                    hourlyForecasts.get(0).getIconPhrase(),
                    "41",
                    dailyForecasts
            );
            presenter.onDataUpdateSuccessful(mainScreenModel);
        }
    }

}
