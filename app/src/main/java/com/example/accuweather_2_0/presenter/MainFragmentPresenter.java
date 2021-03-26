package com.example.accuweather_2_0.presenter;

import android.util.Log;

import com.example.accuweather_2_0.contract.MainFragmentContract;
import com.example.accuweather_2_0.model.MainScreenModel;


public class MainFragmentPresenter implements MainFragmentContract.Presenter {
    private final MainFragmentContract.View view;

    public MainFragmentPresenter(MainFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void updateMainScreenUI(MainScreenModel mainScreenModel) {
        Log.i("dev", "MainFragmentPresenter updateMainScreenUI + mainSkrean " + mainScreenModel.getCards().get(0).toString());
        view.updateMainScreenUI(mainScreenModel);
    }

    @Override
    public void openDetailsFragment() {

    }

}
