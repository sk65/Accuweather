package com.example.accuweather_2_0.model.enams;

import com.example.accuweather_2_0.R;

public enum WindIcons {
    N(R.drawable.ic_north),
    NNE(R.drawable.ic_north_north_east),
    NE(R.drawable.ic_north_east),
    ENE(R.drawable.ic_east_north_east),
    E(R.drawable.ic_east),
    ESE(R.drawable.ic_east_south_east),
    SE(R.drawable.ic_south_east),
    SSE(R.drawable.ic_south_south_east),
    S(R.drawable.ic_south),
    SSW(R.drawable.ic_south_south_west),
    SW(R.drawable.ic_south_west),
    WSW(R.drawable.ic_west_south_west),
    W(R.drawable.ic_west),
    WNW(R.drawable.ic_west_north_west),
    NW(R.drawable.ic_north_west),
    NNW(R.drawable.ic_north_north_west);


    private final int direction;

    public int getDirection() {
        return direction;
    }

    WindIcons(int direction) {
        this.direction = direction;
    }
}
