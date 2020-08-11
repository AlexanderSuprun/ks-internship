package com.example.ks_internship.screen.history;

import com.example.ks_internship.utils.AppPrefsManager;

public class HistoryPresenter implements HistoryContract.Presenter {

    private AppPrefsManager prefsManager;

    public HistoryPresenter(AppPrefsManager prefsManager) {
        this.prefsManager = prefsManager;
    }

    @Override
    public void takeView(HistoryContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
