package com.example.accuweather_2_0.presenter;


import com.example.accuweather_2_0.DetailsFragmentContract;
import com.example.accuweather_2_0.model.screen.DetailsScreenModel;

public class DetailsFragmentPresenter implements DetailsFragmentContract.Presenter {
    private final DetailsFragmentContract.View view;

    public DetailsFragmentPresenter(DetailsFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void updateMainScreenUI(DetailsScreenModel detailsScreenModel) {
        view.updateDetailsScreenUI(detailsScreenModel);
    }

}
