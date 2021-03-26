package com.example.accuweather_2_0.contract;


import com.example.accuweather_2_0.model.MainScreenModel;

public interface MainFragmentContract {
    interface View {
        void updateMainScreenUI(MainScreenModel mainScreenModel);
    }

    interface Presenter {
        void updateMainScreenUI(MainScreenModel mainScreenModel);

        void openDetailsFragment();
    }

    interface Model {
        void requestData();
    }
}
