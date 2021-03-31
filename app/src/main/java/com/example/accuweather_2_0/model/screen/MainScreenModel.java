package com.example.accuweather_2_0.model.screen;


import com.example.accuweather_2_0.model.weather.DailyForecast;

import java.util.List;

public class MainScreenModel {
    private final String todayTemperatureText;
    private final String todayDescription;
    private final String todayAiQualityIndex;
    private final String city;
    private final List<DailyForecast> cards;

    public MainScreenModel(String city,
                           String todayTemperatureText,
                           String todayDescription,
                           String todayAiQualityIndex,
                           List<DailyForecast> cards) {
        this.city = city;
        this.todayTemperatureText = todayTemperatureText;
        this.todayDescription = todayDescription;
        this.todayAiQualityIndex = todayAiQualityIndex;
        this.cards = cards;
    }

    public String getTodayTemperature() {
        return todayTemperatureText;
    }

    public String getTodayDescription() {
        return todayDescription;
    }

    public String getTodayAiQualityIndex() {
        return todayAiQualityIndex;
    }

    public String getTodayTemperatureText() {
        return todayTemperatureText;
    }

    public List<DailyForecast> getCards() {
        return cards;
    }

    public String getCity() {
        return city;
    }
}
