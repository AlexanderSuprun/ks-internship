package com.example.ks_internship.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.base.BaseActivity;
import com.example.ks_internship.screen.main.MainFragment;
import com.example.ks_internship.screen.webview.WebViewContract;
import com.example.ks_internship.screen.webview.WebViewFragment;
import com.example.ks_internship.screen.webview.WebViewPresenter;
import com.example.ks_internship.utils.Constants;

public class WebViewActivity extends BaseActivity {

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebViewContract.Presenter presenter = new WebViewPresenter();
        fragmentContainer = findViewById(R.id.fragment_container);
        WebViewFragment webViewFragment = new WebViewFragment();

        if (!getSupportFragmentManager().getFragments().isEmpty()) {
            webViewFragment = (WebViewFragment) getSupportFragmentManager().findFragmentById(fragmentContainer.getId());
        } else {
            getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), webViewFragment).commit();
        }

        if (getIntent().getStringExtra(Constants.REPO_URL) != null) {
            presenter = new WebViewPresenter(getIntent().getStringExtra(Constants.REPO_URL));
        }
        if (webViewFragment != null) {
            webViewFragment.setPresenter(presenter);
        }
    }
}
