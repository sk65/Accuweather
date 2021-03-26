package com.example.accuweather_2_0.model;



import com.example.accuweather_2_0.model.weather.DailyForecast;

import java.util.List;

public class MainScreenModel {
    private String todayTemperatureText;
    private String todayDescription;
    private String todayAiQualityIndex;

    private List<DailyForecast> cards;

    public MainScreenModel(String todayTemperatureText,
                           String todayDescription,
                           String todayAiQualityIndex,
                           List<DailyForecast> cards) {
        this.todayTemperatureText = todayTemperatureText;
        this.todayDescription = todayDescription;
        this.todayAiQualityIndex = todayAiQualityIndex;
        this.cards = cards;
    }

    public String getTodayTemperature() {
        return todayTemperatureText;
    }

    public void setTodayTemperatureText(String todayTemperatureText) {
        this.todayTemperatureText = todayTemperatureText;
    }

    public String getTodayDescription() {
        return todayDescription;
    }

    public void setTodayDescription(String todayDescription) {
        this.todayDescription = todayDescription;
    }

    public String getTodayAiQualityIndex() {
        return todayAiQualityIndex;
    }

    public void setTodayAiQualityIndex(String todayAiQualityIndex) {
        this.todayAiQualityIndex = todayAiQualityIndex;
    }

    public String getTodayTemperatureText() {
        return todayTemperatureText;
    }

    public List<DailyForecast> getCards() {
        return cards;
    }

    public void setCards(List<DailyForecast> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "MainScreenModel{" +
                "todayTemperatureText='" + todayTemperatureText + '\'' +
                ", todayDescription='" + todayDescription + '\'' +
                ", todayAiQualityIndex='" + todayAiQualityIndex + '\'' +
                ", cards=" + cards +
                '}';
    }
}
