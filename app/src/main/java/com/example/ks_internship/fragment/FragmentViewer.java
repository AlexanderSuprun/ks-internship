package com.example.ks_internship.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.ks_internship.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentViewer extends Fragment {

    WebView webView;
    ProgressBar progressBar;

    public FragmentViewer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        webView = view.findViewById(R.id.fragment_viewer_webview);
        progressBar = view.findViewById(R.id.fragment_viewer_pb);

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

    public void loadWebview(String url) {
        if (url != null) {
            webView.loadUrl(url);
        }
    }
}