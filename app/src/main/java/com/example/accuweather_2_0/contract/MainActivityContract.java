package com.example.accuweather_2_0.contract;


import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.accuweather_2_0.model.MainScreenModel;

public interface MainActivityContract {
    interface View {
        Context getContext();

        void requestPermissions();

        void showLocationUpdateDialog();

        void cancelLocationUpdateDialog();

        FragmentManager getSupportFragmentManager();

        void showNetworkUpdateDialog();

        void cancelNetworkUpdateDialog();

        void onResponseFailure(String message);
    }

    interface Presenter {
        void updateWeatherData();

        void requestPermissions();

        void stopUpdatesLocation();

        void onDataUpdateSuccessful(MainScreenModel mainScreenModel);


        void onDataUpdateFailure(String massage);
    }

    public interface Model {
        void getData();

        void updateLocation();
    }
}
