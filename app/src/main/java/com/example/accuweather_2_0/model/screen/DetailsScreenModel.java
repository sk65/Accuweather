package com.example.accuweather_2_0.model.screen;

import com.example.accuweather_2_0.model.WeatherParamCardModel;
import com.example.accuweather_2_0.model.weather.HourlyForecast;

import java.util.Date;
import java.util.List;

public class DetailsScreenModel {
    private final List<WeatherParamCardModel> weatherParamCardModels;
    private final List<HourlyForecast> hourlyForecasts;
    private final Date sunrise;
    private final Date sunset;
    private final String airQuality;

    public DetailsScreenModel(List<WeatherParamCardModel> weatherParamCardModels,
                              List<HourlyForecast> hourlyForecasts,
                              Date sunrise,
                              Date sunset,
                              String airQuality) {
        this.weatherParamCardModels = weatherParamCardModels;
        this.hourlyForecasts = hourlyForecasts;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.airQuality = airQuality;
    }

    public List<WeatherParamCardModel> getWeatherParamCardModels() {
        return weatherParamCardModels;
    }

    public List<HourlyForecast> getHourlyForecasts() {
        return hourlyForecasts;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public String getAirQuality() {
        return airQuality;
    }
}
