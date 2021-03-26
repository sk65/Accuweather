package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    @SerializedName("DailyForecasts")
    private List<DailyForecast> dailyForecasts;

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }
}
