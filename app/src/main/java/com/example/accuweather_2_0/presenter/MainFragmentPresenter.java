package com.example.accuweather_2_0.presenter;

import com.example.accuweather_2_0.MainFragmentContract;
import com.example.accuweather_2_0.model.screen.MainScreenModel;
import com.example.accuweather_2_0.view.MainActivity;


public class MainFragmentPresenter implements MainFragmentContract.Presenter {
    private final MainFragmentContract.View view;

    public MainFragmentPresenter(MainFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void updateMainScreenUI(MainScreenModel mainScreenModel) {
        view.updateMainScreenUI(mainScreenModel);
    }

    @Override
    public void openDetailsFragment() {
        MainActivity.viewPager.setCurrentItem(1);
    }

}
