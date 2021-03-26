package com.example.accuweather_2_0.contract;

public interface DetailsFragmentContract {
    interface View {
        void initView();
    }

    interface Presenter {
        void initView();
    }

    interface Model {
        void requestData();
    }
}
