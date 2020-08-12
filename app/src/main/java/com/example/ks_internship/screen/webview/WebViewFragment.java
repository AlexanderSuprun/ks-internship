package com.example.ks_internship.screen.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.base.BaseFragment;

public class WebViewFragment extends BaseFragment implements WebViewContract.View {

    private WebView webView;
    private ProgressBar progressBar;
    private WebViewContract.Presenter presenter;

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        initToolbarWithNavigation(getString(R.string.github_repository_title), view);
        webView = view.findViewById(R.id.fragment_viewer_webview);
        progressBar = view.findViewById(R.id.fragment_viewer_pb);

        presenter.takeView(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if(newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                progressBar.setProgress(newProgress);

                if(newProgress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });
    }

    @Override
    public void loadWebview(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void setPresenter(WebViewContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
