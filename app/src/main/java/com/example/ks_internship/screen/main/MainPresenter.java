package com.example.ks_internship.screen.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.ks_internship.model.GitRepoError;
import com.example.ks_internship.model.GitRepoItem;
import com.example.ks_internship.utils.AppPrefsManager;
import com.example.ks_internship.utils.database.AppDatabase;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private AppPrefsManager prefsManager;
    private AppDatabase database;
    private LiveData<GitRepoItem> itemsLiveData;

    public MainPresenter(AppPrefsManager prefsManager, AppDatabase database) {
        this.prefsManager = prefsManager;
        this.database = database;
    }


    @Override
    public void searchRepos(@NonNull String query) {

    }

    @Override
    public void takeView(MainContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
