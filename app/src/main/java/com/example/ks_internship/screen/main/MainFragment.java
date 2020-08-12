package com.example.ks_internship.screen.main;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.WebViewActivity;
import com.example.ks_internship.base.BaseFragment;
import com.example.ks_internship.model.GitRepoItem;
import com.example.ks_internship.utils.Constants;
import com.example.ks_internship.utils.KeyboardUtils;
import com.example.ks_internship.utils.adapter.GitRepoRecyclerAdapter;
import com.example.ks_internship.utils.listener.OnGitRepoRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment implements MainContract.View {

    private RecyclerView recyclerView;
    private View loaderBlock;

    private AppCompatEditText usernameInput;
    private AppCompatButton btnSearch;
    private GitRepoRecyclerAdapter adapter;
    private ArrayList<GitRepoItem> gitRepoItems;

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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initToolbarWithHistoryAction(getString(R.string.app_name), view);
        initViews(view);
        gitRepoItems = new ArrayList<>();

        adapter = new GitRepoRecyclerAdapter(gitRepoItems, new OnGitRepoRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Uri url) {
                openRepo(url);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchRepos(usernameInput.getText().toString());
            }
        });

        usernameInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    presenter.searchRepos(usernameInput.getText().toString());
                    return true;
                }
                return false;
            }
        });

        presenter.takeView(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    private void openRepo(Uri url) {
        try {
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra(Constants.REPO_URL, url.toString());
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showInputError() {
        usernameInput.requestFocus();
    }

    @Override
    public void showRequestError(@NonNull String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void observeItems(LiveData<List<GitRepoItem>> itemsLiveData) {
        itemsLiveData.observe(MainFragment.this, new Observer<List<GitRepoItem>>() {
            @Override
            public void onChanged(List<GitRepoItem> items) {
                gitRepoItems.clear();
                gitRepoItems.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void stopObserving(LiveData<List<GitRepoItem>> liveRepoData) {
        liveRepoData.removeObservers(MainFragment.this);
    }

    @Override
    public void showProgress() {
        loaderBlock.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loaderBlock.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtils.hide(usernameInput);
    }

    private void initViews(View v) {
        loaderBlock = v.findViewById(R.id.fragment_chooser_loader_block);
        recyclerView = v.findViewById(R.id.fragment_chooser_rv);
        usernameInput = v.findViewById(R.id.fragment_chooser_et_username);
        btnSearch = v.findViewById(R.id.fragment_chooser_btn_search);
    }
}
