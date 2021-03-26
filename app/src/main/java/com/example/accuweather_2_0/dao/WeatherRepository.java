package com.example.accuweather_2_0.dao;

import com.example.accuweather_2_0.contract.MainActivityContract;
import com.example.accuweather_2_0.model.MainScreenModel;
import com.example.accuweather_2_0.presenter.MainActivityPresenter;

public interface WeatherRepository {



    void requestDataFromServer(MainActivityPresenter mainActivityPresenter);
}
