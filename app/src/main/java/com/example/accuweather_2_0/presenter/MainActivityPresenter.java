package com.example.accuweather_2_0.presenter;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.accuweather_2_0.SharedPreferencesManager;
import com.example.accuweather_2_0.MainActivityContract;
import com.example.accuweather_2_0.dao.WeatherRepository;
import com.example.accuweather_2_0.dao.WeatherRepositoryImpl;
import com.example.accuweather_2_0.model.screen.DetailsScreenModel;
import com.example.accuweather_2_0.model.screen.MainScreenModel;
import com.example.accuweather_2_0.view.fragment.DetailsFragment;
import com.example.accuweather_2_0.view.fragment.MainFragment;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private final MainActivityContract.View view;
    private final WeatherRepository weatherRepository;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        weatherRepository = new WeatherRepositoryImpl(this);
    }

    @Override
    public void updateLocationAndWeatherData() {
        view.showLocationUpdateDialog();
        weatherRepository.updateLocationAndWeatherData();
    }

    @Override
    public void updateWeatherData() {
        view.showNetworkUpdateDialog();
        weatherRepository.updateWeatherData();
    }

    @Override
    public void requestPermissions() {
        view.requestPermissions();
    }

    @Override
    public void stopUpdatesLocation() {
        weatherRepository.stopUpdatesLocation();
        view.cancelLocationUpdateDialog();
    }

    @Override
    public void onDataUpdateSuccessful(MainScreenModel mainScreenModel, DetailsScreenModel detailsScreenModel) {
        view.cancelNetworkUpdateDialog();
        List<Fragment> fragments = view.getSupportFragmentManager().getFragments();
        MainFragment mainFragment = (MainFragment) fragments.get(0);
        DetailsFragment detailsFragment = (DetailsFragment) fragments.get(1);
        mainFragment.getPresenter().updateMainScreenUI(mainScreenModel);
        detailsFragment.getPresenter().updateMainScreenUI(detailsScreenModel);
    }

    @Override
    public void onDataUpdateFailure(String massage) {
        view.cancelNetworkUpdateDialog();
        view.onResponseFailure(massage);
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void cancelLocationUpdateDialog() {
        view.cancelLocationUpdateDialog();
    }

    @Override
    public void refreshUI() {
        MainScreenModel mainScreenModel = SharedPreferencesManager.getInstance().getMainScreenModel();
        DetailsScreenModel detailsScreenModel = SharedPreferencesManager.getInstance().geDetailsScreenModel();
        if (mainScreenModel != null && detailsScreenModel != null) {
            onDataUpdateSuccessful(mainScreenModel, detailsScreenModel);
        }

    }

    @Override
    public void showNetworkProgressDialog() {
        view.showNetworkUpdateDialog();
    }

}
