package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class DailyForecast {
    @SerializedName("Date")
    private Date date;

    @SerializedName("Temperature")
    private Temperature temperature;
    @SerializedName("RealFeelTemperature")
    private RealFeelTemperature realFeelTemperature;
    @SerializedName("Day")
    private Day day;
    @SerializedName("AirAndPollen")
    private List<AirAndPollen> airAndPollen;
    @SerializedName("Sun")
    private Sun sun;

    public RealFeelTemperature getRealFeelTemperature() {
        return realFeelTemperature;
    }

    public List<AirAndPollen> getAirAndPollen() {
        return airAndPollen;
    }

    public Sun getSun() {
        return sun;
    }

    public Date getDate() {
        return date;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Day getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "DailyForecast{" +
                "date=" + date +
                ", temperature=" + temperature +
                ", day=" + day +
                '}';
    }
}
