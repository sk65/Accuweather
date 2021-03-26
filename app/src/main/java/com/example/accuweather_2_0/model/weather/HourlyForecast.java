package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class HourlyForecast {
    @SerializedName("DateTime")
    private Date dateTime;

    @SerializedName("WeatherIcon")
    private int weatherIcon;

    @SerializedName("IconPhrase")
    private String iconPhrase;

    @SerializedName("Temperature")
    private CurrentTemperature temperature;

    @SerializedName("RealFeelTemperature")
    private RealFeelTemperature realFeelTemperature;

    @SerializedName("Wind")
    private Wind wind;

    @SerializedName("Ceiling")
    public Ceiling ceiling;

    @SerializedName("UVIndex")
    private int uVIndex;

    @SerializedName("RainProbability")
    private int rainProbability;

    @SerializedName("SnowProbability")
    private int snowProbability;

    @SerializedName("IceProbability")
    private int iceProbability;

    public Date getDateTime() {
        return dateTime;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }

    public String getIconPhrase() {
        return iconPhrase;
    }

    public CurrentTemperature getTemperature() {
        return temperature;
    }

    public RealFeelTemperature getRealFeelTemperature() {
        return realFeelTemperature;
    }

    public Wind getWind() {
        return wind;
    }

    public Ceiling getCeiling() {
        return ceiling;
    }

    public int getuVIndex() {
        return uVIndex;
    }

    public int getRainProbability() {
        return rainProbability;
    }

    public int getSnowProbability() {
        return snowProbability;
    }

    public int getIceProbability() {
        return iceProbability;
    }

    @Override
    public String toString() {
        return "DayForecast{" +
                "dateTime=" + dateTime +
                ", weatherIcon=" + weatherIcon +
                ", iconPhrase='" + iconPhrase + '\'' +
                ", temperature=" + temperature.getValue() + "}";

    }
}