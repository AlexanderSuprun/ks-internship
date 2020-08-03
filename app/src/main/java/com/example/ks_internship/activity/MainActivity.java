package com.example.ks_internship.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.api.ApiCallback;
import com.example.ks_internship.api.RestClient;
import com.example.ks_internship.fragment.FragmentChooser;
import com.example.ks_internship.fragment.FragmentViewer;
import com.example.ks_internship.model.GitRepoError;
import com.example.ks_internship.model.GitRepoItem;
import com.example.ks_internship.utils.AppPrefsManager;
import com.example.ks_internship.utils.Constants;
import com.example.ks_internship.utils.adapter.GitRepoRecyclerAdapter;
import com.example.ks_internship.utils.listeners.OnGitRepoRecyclerItemClickListener;
import com.example.ks_internship.utils.listeners.OnGitRepoSearchAction;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Fills HashMap with Cat objects.
 * If in portrait mode, contains {@link FragmentChooser} and starts
 * {@link WebViewActivity} with {@link FragmentViewer} in it.
 * Contains both {@link FragmentChooser} and {@link FragmentViewer}, if in landscape mode.
 */

public class MainActivity extends BaseActivity {

    private FragmentChooser fragmentChooser;
    private FragmentViewer fragmentViewer;
    private GitRepoRecyclerAdapter adapter;

    private ArrayList<GitRepoItem> items = new ArrayList<>();

    private boolean inLandscapeMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbarWithHistoryOption(getString(R.string.app_name));

        adapter = new GitRepoRecyclerAdapter(items, new OnGitRepoRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Uri url) {
                openRepo(url);
            }
        });


        inLandscapeMode = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_chooser);

        if (fragmentChooser != null) {
            fragmentChooser.setAdapter(adapter);
            fragmentChooser.setSearchListener(new OnGitRepoSearchAction() {
                @Override
                public void onSearchButtonClick(AppCompatEditText usernameInput) {
                    AppPrefsManager.cacheSearchHistory(MainActivity.this, usernameInput.getText().toString());
                    loadRepos(usernameInput.getText().toString());
                }
            });
        }
        if (inLandscapeMode) {
            fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_viewer);
        }

        getDatabase().repoItemDao().getAllRecords().observe(this, new Observer<List<GitRepoItem>>() {
            @Override
            public void onChanged(List<GitRepoItem> gitRepoItems) {
                items.clear();
                items.addAll(gitRepoItems);
                adapter.notifyDataSetChanged();
            }
        });

        if (getIntent().getExtras() != null) {
            String username = getIntent().getStringExtra(Constants.HISTORY_TITLE);
            loadRepos(username);
            fragmentChooser.setUsernameInput(username);
        }
    }

    private void openRepo(Uri url) {
        if (inLandscapeMode) {
            fragmentViewer.loadWebview(url.toString());
        } else {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            intent.putExtra(Constants.REPO_URL, url.toString());
            startActivity(intent);
        }
    }

    private void loadRepos(String username) {
        fragmentChooser.showLoaderBlock();
        RestClient.getInstance().getService().searchReposByUsername(username).enqueue(new ApiCallback<List<GitRepoItem>>() {
            @Override
            public void success(Response<List<GitRepoItem>> response) {
                updateList(response.body());
                fragmentChooser.hideLoaderBlock();
            }

            @Override
            public void failure(GitRepoError gitRepoError) {
                if (TextUtils.isEmpty(gitRepoError.getDocURL())) {
                    makeErrorToast(gitRepoError.getMessage());
                } else {
                    makeErrorToast(gitRepoError.getMessage() + "Details: " + gitRepoError.getDocURL());
                }
                fragmentChooser.hideLoaderBlock();
            }
        });
    }

    private void updateList(List<GitRepoItem> itemsToUpdate) {
        getDatabase().repoItemDao().deleteAllRecords();
        getDatabase().repoItemDao().insert(itemsToUpdate);
    }

    private void makeErrorToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}