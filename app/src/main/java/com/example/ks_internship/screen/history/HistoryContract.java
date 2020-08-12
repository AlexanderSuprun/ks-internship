package com.example.ks_internship.screen.history;

import com.example.ks_internship.base.BasePresenter;
import com.example.ks_internship.base.BaseView;

import java.util.ArrayList;

public interface HistoryContract {

    interface View extends BaseView<Presenter> {
        void setHistoryItems(ArrayList<String> items);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
