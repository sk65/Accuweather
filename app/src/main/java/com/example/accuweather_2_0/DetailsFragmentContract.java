package com.example.accuweather_2_0;

import com.example.accuweather_2_0.model.screen.DetailsScreenModel;

public interface DetailsFragmentContract {
    interface View {
        void updateDetailsScreenUI(DetailsScreenModel detailsScreenModel);
    }

    interface Presenter {
        void updateMainScreenUI(DetailsScreenModel detailsScreenModel);
    }
}
