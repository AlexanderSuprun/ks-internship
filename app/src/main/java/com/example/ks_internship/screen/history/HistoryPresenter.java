package com.example.ks_internship.screen.history;

import android.util.Log;

import com.example.ks_internship.utils.AppPrefsManager;

import java.util.ArrayList;

public class HistoryPresenter implements HistoryContract.Presenter {

    private HistoryContract.View view;
    private AppPrefsManager prefsManager;

    public HistoryPresenter(AppPrefsManager prefsManager) {
        this.prefsManager = prefsManager;
    }

    @Override
    public void takeView(HistoryContract.View view) {
        this.view = view;
        view.setHistoryItems((ArrayList<String>) prefsManager.getCachedSearchHistory());
    }

    @Override
    public void dropView() {
        this.view = null;
    }

}
