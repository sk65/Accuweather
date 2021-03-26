package com.example.accuweather_2_0.presenter;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import com.example.accuweather_2_0.App;
import com.example.accuweather_2_0.contract.MainActivityContract;
import com.example.accuweather_2_0.dao.WeatherRepository;
import com.example.accuweather_2_0.dao.WeatherRepositoryImpl;
import com.example.accuweather_2_0.model.MainScreenModel;
import com.example.accuweather_2_0.view.fragment.MainFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private final MainActivityContract.View view;
    private final WeatherRepository weatherRepository;
    //location
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;


    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        weatherRepository = new WeatherRepositoryImpl();
    }

    @Override
    public void updateWeatherData() {
        Context context = view.getContext();
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            Log.i("dev", "MainPresenter permissions denied");
            return;
        }
        view.showLocationUpdateDialog();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        createLocationCallback();
        createLocationRequest();
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback, Looper.myLooper());
    }

    @Override
    public void requestPermissions() {
        view.requestPermissions();
    }

    @Override
    public void stopUpdatesLocation() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
        view.cancelLocationUpdateDialog();
    }

    @Override
    public void onDataUpdateSuccessful(MainScreenModel mainScreenModel) {
        view.cancelNetworkUpdateDialog();
        List<Fragment> fragments = view.getSupportFragmentManager().getFragments();
        MainFragment mainFragment = (MainFragment) fragments.get(0);
        mainFragment.getPresenter().updateMainScreenUI(mainScreenModel);
    }


    @Override
    public void onDataUpdateFailure(String massage) {
        view.cancelNetworkUpdateDialog();
        view.onResponseFailure(massage);
    }

    private void requestWeatherDataFromServer() {
        view.showLocationUpdateDialog();
        weatherRepository.requestDataFromServer(this);

    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NotNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                App.currentLocation = locationResult.getLastLocation();
                requestWeatherDataFromServer();
                fusedLocationClient.removeLocationUpdates(locationCallback);
                view.cancelLocationUpdateDialog();
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
