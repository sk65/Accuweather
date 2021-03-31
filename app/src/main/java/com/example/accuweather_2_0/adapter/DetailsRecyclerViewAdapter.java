package com.example.accuweather_2_0.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.model.weather.HourlyForecast;
import com.example.accuweather_2_0.utils.Utils;

import java.util.List;


public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<DetailsRecyclerViewAdapter.MyViewHolder> {
    private final Context context;
    private final List<HourlyForecast> model;

    public DetailsRecyclerViewAdapter(Context context, List<HourlyForecast> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_details_fragment, parent, false);
        return new DetailsRecyclerViewAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.weatherIcon.setImageResource(Utils.getWeatherIcon(model.get(position).getWeatherIcon()));
        holder.windDirection.setImageResource(Utils.getWindIcon(model.get(position).getWind().getDirection().getValue()));
        holder.currentTime.setText(Utils.getCurrentHour(context, model.get(position).getDateTime(), position));
        holder.currentTimeTemperature.setText(Math.round(model.get(position).getTemperature().getValue()) + context.getString(R.string.degree));
        holder.currentWindSpeed.setText(model.get(position).getWind().getSpeed().getValue() + context.getString(R.string.wind_speed_measuring));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView currentTime;
        private final TextView currentTimeTemperature;
        private final TextView currentWindSpeed;
        private final ImageView weatherIcon;
        private final ImageView windDirection;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            currentTime = itemView.findViewById(R.id.textView_timeHolder);
            currentTimeTemperature = itemView.findViewById(R.id.textView_temperatureHolder);
            currentWindSpeed = itemView.findViewById(R.id.textView_wind_speed);
            weatherIcon = itemView.findViewById(R.id.imageView_list_item_iconHolder);
            windDirection = itemView.findViewById(R.id.imageView_windDirection);
        }
    }
}
