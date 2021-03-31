package com.example.accuweather_2_0.adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.accuweather_2_0.view.fragment.DetailsFragment;
import com.example.accuweather_2_0.view.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;


    public SectionsPagerAdapter( @NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        Fragment mainFragment = new MainFragment();
        Fragment detailsFragment = new DetailsFragment();
        fragments = new ArrayList<>(2);
        fragments.add(mainFragment);
        fragments.add(detailsFragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
