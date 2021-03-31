package com.example.accuweather_2_0.model.enams;


import com.example.accuweather_2_0.R;

public enum WeatherIcons {
    SUNNY(R.drawable.ic_weather_1, 1),
    MOSTLY_SUNNY(R.drawable.ic_weather_2, 2),
    PARTLY_SUNNY(R.drawable.ic_weather_3, 3),
    INTERMITTENT_CLOUDS(R.drawable.ic_weather_4, 4),
    HAZY_SUNSHINE(R.drawable.ic_weather_5, 5),
    MOSTLY_CLOUDY(R.drawable.ic_weather_6, 6),
    CLOUDY(R.drawable.ic_weather_7, 7),
    DREARY(R.drawable.ic_weather_8, 8),
    FOG(R.drawable.ic_weather_11, 11),
    SHOWERS(R.drawable.ic_weather_12, 12),
    MOSTLY_CLOUDY_W_SHOWERS(R.drawable.ic_weather_13, 13),
    PARTLY_SUNNY_W_SHOWERS(R.drawable.ic_weather_14, 14),
    T_STORMS(R.drawable.ic_weather_15, 15),
    MOSTLY_CLOUDY_T_STORMS(R.drawable.ic_weather_16, 16),
    PARTLY_SUNNY_W_T_STORMS(R.drawable.ic_weather_17, 17),
    RAIN(R.drawable.ic_weather_18, 18),
    FLURRIES(R.drawable.ic_weather_19, 19),
    MOSTLY_CLOUDY_W_FLURRIES(R.drawable.ic_weather_20, 20),
    SNOW(R.drawable.ic_weather_22, 22),
    MOSTLY_CLOUDY_W_SNOW(R.drawable.ic_weather_23, 23),
    ICE(R.drawable.ic_weather_24, 24),
    SLEET(R.drawable.ic_weather_25, 25),
    FREEZING_RAIN(R.drawable.ic_weather_26, 26),
    RAIN_AND_SNOW(R.drawable.ic_weather_29, 29),
    HOT(R.drawable.ic_weather_30, 30),
    COLD(R.drawable.ic_weather_31, 31),
    WINDY(R.drawable.ic_weather_32, 32),
    CLEAR(R.drawable.ic_weather_33, 33),
    MOSTLY_CLEAR(R.drawable.ic_weather_34, 34),
    PARTLY_CLOUDY(R.drawable.ic_weather_35, 35),
    INTERMITTENT_CLOUDY(R.drawable.ic_weather_36, 36),
    HAZY_MOONLIGHT(R.drawable.ic_weather_37, 37),
    MOSTLY_CLOUDY_NIGHT(R.drawable.ic_weather_38, 38),
    PARTLY_CLOUDY_W_SHOWERS_NIGHT(R.drawable.ic_weather_39, 39),
    MOSTLY_CLOUDY_W_SHOWERS_NIGHT(R.drawable.ic_weather_40, 40),
    PARTLY_CLOUDY_T_STORMS(R.drawable.ic_weather_41, 41),
    MOSTLY_CLOUDY_W_FLURRIES_NIGHT(R.drawable.ic_weather_42, 42),
    MOSTLY_CLOUDY_SNOW_NIGHT(R.drawable.ic_weather_43, 43);

    private final int iconRes;

    public int getIconKey() {
        return iconKey;
    }

    private final int iconKey;

    WeatherIcons(int iconRes, int iconKey) {

        this.iconRes = iconRes;
        this.iconKey = iconKey;
    }

    public int getIconRes() {
        return iconRes;
    }
}
