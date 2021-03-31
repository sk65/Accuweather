package com.example.accuweather_2_0.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.adapter.DetailsGridViewAdapter;
import com.example.accuweather_2_0.adapter.DetailsRecyclerViewAdapter;
import com.example.accuweather_2_0.DetailsFragmentContract;
import com.example.accuweather_2_0.model.screen.DetailsScreenModel;
import com.example.accuweather_2_0.presenter.DetailsFragmentPresenter;
import com.example.accuweather_2_0.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailsFragment extends Fragment implements DetailsFragmentContract.View {
    public static final String TAG = DetailsFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private GridView gridView;
    private TextView airQualityTextView;
    private TextView sunriseTextView;
    private TextView sunsetTextView;
    private DetailsFragmentContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DetailsFragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar_fragmentDetails);
        sunriseTextView = view.findViewById(R.id.textView_detailsFragment_sunrise_value);
        sunsetTextView = view.findViewById(R.id.textView_detailsFragment_sunset_value);
        airQualityTextView = view.findViewById(R.id.textView_detailsFragment_air_quality_index_value);
        //recycler view
        recyclerView = view.findViewById(R.id.recyclerView_fragmentDetails_hourly_forecast);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        gridView = view.findViewById(R.id.gridView_fragmentDetails);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void updateDetailsScreenUI(DetailsScreenModel detailsScreenModel) {
        Date currentDate = new Date();
        if (!currentDate.before(detailsScreenModel.getSunrise()) && !currentDate.after(detailsScreenModel.getSunset())) {
            double sunRiseTime = Double.parseDouble(new SimpleDateFormat("kk.mm").format(detailsScreenModel.getSunrise()));
            double sunSetTime = Double.parseDouble(new SimpleDateFormat("kk.mm").format(detailsScreenModel.getSunset()));
            double currentTime = Double.parseDouble(new SimpleDateFormat("kk.mm").format(currentDate));
            double progressBarMaxValue = sunSetTime * 60 - sunRiseTime * 60;
            progressBar.setMax((int) progressBarMaxValue);
            int progress = (int) (currentTime * 60 - sunRiseTime * 60);
            progressBar.setProgress(progress);
        } else {
            progressBar.setProgress(100);
        }
        sunriseTextView.setText(" " + Utils.getCurrentHour(detailsScreenModel.getSunrise()));
        sunsetTextView.setText(" " + Utils.getCurrentHour(detailsScreenModel.getSunset()));
        airQualityTextView.setText(detailsScreenModel.getAirQuality());
        DetailsRecyclerViewAdapter viewAdapter = new DetailsRecyclerViewAdapter(getContext(), detailsScreenModel.getHourlyForecasts());
        recyclerView.setAdapter(viewAdapter);
        DetailsGridViewAdapter gridViewAdapter = new DetailsGridViewAdapter(getContext(), detailsScreenModel.getWeatherParamCardModels());
        gridView.setAdapter(gridViewAdapter);
    }

    public DetailsFragmentContract.Presenter getPresenter() {
        return presenter;
    }
}
