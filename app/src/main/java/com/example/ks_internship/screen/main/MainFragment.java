package com.example.ks_internship.screen.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ks_internship.R;
import com.example.ks_internship.base.BaseFragment;
import com.example.ks_internship.model.GitRepoItem;
import com.example.ks_internship.utils.adapter.GitRepoRecyclerAdapter;
import com.example.ks_internship.utils.listener.OnGitRepoSearchAction;

public class MainFragment extends BaseFragment implements MainContract.View {

    private RecyclerView recyclerView;
    private View loaderBlock;

    private AppCompatEditText usernameInput;
    private AppCompatButton btnSearch;
    private GitRepoRecyclerAdapter adapter;
    private OnGitRepoSearchAction searchListener;

    private MainContract.Presenter presenter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chooser, container, false);

        initToolbarWithHistoryAction(getString(R.string.app_name), view);


        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void showInputError() {

    }

    @Override
    public void showRequestError(@NonNull String message) {

    }

    @Override
    public void observeItems(LiveData<GitRepoItem> itemsLiveData) {

    }

    @Override
    public void stopObserving(LiveData<GitRepoItem> liveRepoData) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void hideKeyboard() {

    }
}
