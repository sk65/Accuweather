package com.example.accuweather_2_0.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.model.WeatherParamCardModel;

import java.util.List;

public class DetailsGridViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<WeatherParamCardModel> models;
    private LayoutInflater inflater;

    public DetailsGridViewAdapter(Context context, List<WeatherParamCardModel> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.details_grid_view_row_item, null);
        }
        TextView textViewWeatherDescription = convertView.findViewById(R.id.textView_rowItem_weatherDesc);
        TextView textViewWeatherParam = convertView.findViewById(R.id.textView_rowItem_weatherParam);

        textViewWeatherDescription.setText(models.get(position).getValueDescription());
        textViewWeatherParam.setText(models.get(position).getValue());
        return convertView;
    }
}
