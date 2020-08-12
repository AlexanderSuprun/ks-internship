package com.example.ks_internship.screen.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.example.ks_internship.api.ApiCallback;
import com.example.ks_internship.api.RestClient;
import com.example.ks_internship.model.GitRepoError;
import com.example.ks_internship.model.GitRepoItem;
import com.example.ks_internship.utils.AppPrefsManager;
import com.example.ks_internship.utils.database.AppDatabase;

import java.util.List;

import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private AppPrefsManager prefsManager;
    private AppDatabase database;
    private LiveData<List<GitRepoItem>> itemsLiveData;

    public MainPresenter(AppPrefsManager prefsManager, AppDatabase database) {
        this.prefsManager = prefsManager;
        this.database = database;
    }


    @Override
    public void searchRepos(@NonNull String query) {
        if (query.trim().isEmpty()) {
            view.showInputError();
            return;
        }

        prefsManager.cacheSearchHistory(query);
        view.hideKeyboard();
        view.showProgress();

        RestClient.getInstance().getService().searchReposByUsername(query).enqueue(new ApiCallback<List<GitRepoItem>>() {
            @Override
            public void success(Response<List<GitRepoItem>> response) {
                view.hideProgress();
                updateList(response.body());
            }

            @Override
            public void failure(GitRepoError gitRepoError) {
                view.hideProgress();
                view.showRequestError(gitRepoError.getMessage() + "Details: " + gitRepoError.getDocURL());
            }
        });

    }

    @Override
    public void takeView(MainContract.View view) {
        this.view = view;

        itemsLiveData = database.repoItemDao().getAllRecords();
        view.observeItems(itemsLiveData);
    }

    @Override
    public void dropView() {
        view.stopObserving(itemsLiveData);
        this.view = null;
        itemsLiveData = null;
    }

    private void updateList(List<GitRepoItem> itemsToUpdate) {
        database.repoItemDao().deleteAllRecords();
        database.repoItemDao().insert(itemsToUpdate);
    }
}
