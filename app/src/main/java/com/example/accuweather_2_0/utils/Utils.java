package com.example.accuweather_2_0.utils;

import android.content.Context;
import android.location.Location;

import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.model.enams.WeatherIcons;
import com.example.accuweather_2_0.model.weather.AirAndPollen;
import com.example.accuweather_2_0.model.weather.Temperature;
import com.example.accuweather_2_0.model.enams.WindIcons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {
    public static int getWeatherIcon(int iconKey) {
        int iconRes = R.drawable.ic_weather_1;
        WeatherIcons[] values = WeatherIcons.values();
        for (WeatherIcons value : values)
            if (iconKey == value.getIconKey()) {
                iconRes = value.getIconRes();
            }
        return iconRes;
    }

    public static String getCurrentHour(Context context, Date date, int position) {
        if (position == 0) {
            return context.getString(R.string.now);
        }

        return new SimpleDateFormat("k:mm").format(date.getTime());
    }

    public static String getCurrentHour(Date date) {
        return new SimpleDateFormat("k:mm").format(date.getTime());
    }

    public static String getCurrentDay(Context context, Date date, int position) {
        if (position == 0) {
            return context.getString(R.string.today);
        }
        if (position == 1) {
            return context.getString(R.string.tomorrow);
        }
        return new SimpleDateFormat("EEEE").format(date.getTime());
    }

    public static String getTemperatureRange(Temperature temperature, Context context) {
        String degree = context.getString(R.string.degree);
        return Math.round(temperature.getMinimum().getValue())
                + degree + " / "
                + Math.round(temperature.getMaximum().getValue()) + degree;
    }

    public static String getLocationString(Location location) {
        return location.getLatitude() + "," + location.getLongitude();
    }

    public static int getWindIcon(String iconKey) {
        int windIcon = R.drawable.ic_west;
        WindIcons[] values = WindIcons.values();
        for (WindIcons value : values) {
            if (value.name().equals(iconKey)) {
                windIcon = value.getDirection();
            }
        }
        return windIcon;
    }

    public static String getAirAndPollenValue(String name, List<AirAndPollen> airAndPollen) {
        String result = "";
        for (AirAndPollen value : airAndPollen) {
            if (value.getName().equals(name)) {
                result = String.valueOf(value.getValue());
            }
        }
        return result;
    }
}
