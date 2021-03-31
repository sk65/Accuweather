package com.example.accuweather_2_0.dao;

public interface WeatherRepository {
    void updateLocationAndWeatherData();

    void updateWeatherData();

    void responseToPresenter();

    void stopUpdatesLocation();

}
