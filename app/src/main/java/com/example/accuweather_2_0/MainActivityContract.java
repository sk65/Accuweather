package com.example.accuweather_2_0;


import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.accuweather_2_0.model.screen.DetailsScreenModel;
import com.example.accuweather_2_0.model.screen.MainScreenModel;

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
        void updateLocationAndWeatherData();

        void updateWeatherData();

        void requestPermissions();

        void stopUpdatesLocation();

        void onDataUpdateSuccessful(MainScreenModel mainScreenModel, DetailsScreenModel detailsScreenModel);

        void onDataUpdateFailure(String massage);

        Context getContext();

        void cancelLocationUpdateDialog();

        void refreshUI();

        void showNetworkProgressDialog();
    }

}
