package com.example.ks_internship.screen.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.base.BaseFragment;

public class WebViewFragment extends BaseFragment implements WebViewContract.View {

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);

        initToolbarWithNavigation(getString(R.string.github_repository_title), view);

        return view;
    }

    @Override
    public void setPresenter(WebViewContract.Presenter presenter) {

    }
}
