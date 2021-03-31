package com.example.accuweather_2_0;


import com.example.accuweather_2_0.model.screen.MainScreenModel;

public interface MainFragmentContract {
    interface View {
        void updateMainScreenUI(MainScreenModel mainScreenModel);
    }

    interface Presenter {
        void updateMainScreenUI(MainScreenModel mainScreenModel);

        void openDetailsFragment();
    }
}
