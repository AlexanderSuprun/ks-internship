package com.example.ks_internship.screen.webview;

public class WebViewPresenter implements WebViewContract.Presenter{

    private String url;
    private WebViewContract.View view;

    public WebViewPresenter() {
    }

    public WebViewPresenter(String url) {
        this.url = url;
    }

    @Override
    public void takeView(WebViewContract.View view) {
        this.view = view;

        if (url != null) {
            this.view.loadWebview(url);
        }
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
