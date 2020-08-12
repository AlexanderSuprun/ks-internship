package com.example.ks_internship.screen.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.ks_internship.base.BasePresenter;
import com.example.ks_internship.base.BaseView;
import com.example.ks_internship.model.GitRepoItem;

import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void hideKeyboard();

        void showInputError();

        void showRequestError(@NonNull String message);

        void observeItems(LiveData<List<GitRepoItem>> itemsLiveData);

        void stopObserving(LiveData<List<GitRepoItem>> liveRepoData);
    }

    interface Presenter extends BasePresenter<View> {

        void searchRepos(@NonNull String query);

    }

}
