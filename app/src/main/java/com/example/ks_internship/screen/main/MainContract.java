package com.example.ks_internship.screen.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.ks_internship.base.BasePresenter;
import com.example.ks_internship.base.BaseView;
import com.example.ks_internship.model.GitRepoItem;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void hideKeyboard();

        void showInputError();

        void showRequestError(@NonNull String message);

        void observeItems(LiveData<GitRepoItem> itemsLiveData);

        void stopObserving(LiveData<GitRepoItem> liveRepoData);
    }

    interface Presenter extends BasePresenter<View> {

        void searchRepos(@NonNull String query);



    }

}
