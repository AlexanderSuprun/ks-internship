package com.example.ks_internship.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.base.BaseActivity;
import com.example.ks_internship.fragment.FragmentViewer;
import com.example.ks_internship.utils.Constants;

public class WebViewActivity extends BaseActivity {

    private FragmentViewer fragmentViewer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initToolbarWithNavigation(getString(R.string.github_repository_title));

        fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.activity_webview_fragment_viewer);
        if (fragmentViewer != null && getIntent().getExtras() != null) {
            fragmentViewer.loadWebview(getIntent().getStringExtra(Constants.REPO_URL));
        }
    }
}
