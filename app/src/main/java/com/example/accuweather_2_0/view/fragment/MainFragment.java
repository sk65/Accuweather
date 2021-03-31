package com.example.accuweather_2_0.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.adapter.MainRecyclerViewAdapter;
import com.example.accuweather_2_0.MainFragmentContract;
import com.example.accuweather_2_0.model.screen.MainScreenModel;
import com.example.accuweather_2_0.presenter.MainFragmentPresenter;

import java.util.LinkedList;


public class MainFragment extends Fragment implements MainFragmentContract.View {

    public final static String TAG = MainFragment.class.getSimpleName();

    private MainFragmentContract.Presenter presenter;
    private TextView todayTemperatureTextView;
    private TextView todayDescriptionTextView;
    private TextView todayAiQualityIndexTextView;
    private TextView cityHolderTextView;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainFragmentPresenter(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cityHolderTextView = view.findViewById(R.id.textView_mainFragment_cityHolder);
        todayTemperatureTextView = view.findViewById(R.id.textView_mainFragment_todayTemperature);
        todayDescriptionTextView = view.findViewById(R.id.textView_mainFragment_todayDescription);
        todayAiQualityIndexTextView = view.findViewById(R.id.textView_mainFragment_today_airQualityIndex);

        view.findViewById(R.id.button_mainFragment_details).
                setOnClickListener((v) -> presenter.openDetailsFragment());

        recyclerView = view.findViewById(R.id.recyclerView_mainFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MainRecyclerViewAdapter viewAdapter = new MainRecyclerViewAdapter(getContext(), new LinkedList<>());
        recyclerView.setAdapter(viewAdapter);
    }

    @Override
    public void updateMainScreenUI(MainScreenModel mainScreenModel) {
        todayTemperatureTextView.setText(mainScreenModel.getTodayTemperature());
        todayDescriptionTextView.setText(mainScreenModel.getTodayDescription());
        todayAiQualityIndexTextView.setText(mainScreenModel.getTodayAiQualityIndex());
        cityHolderTextView.setText(mainScreenModel.getCity());
        MainRecyclerViewAdapter viewAdapter = new MainRecyclerViewAdapter(getContext(), mainScreenModel.getCards());
        recyclerView.setAdapter(viewAdapter);


    }

    public MainFragmentContract.Presenter getPresenter() {
        return presenter;
    }
}