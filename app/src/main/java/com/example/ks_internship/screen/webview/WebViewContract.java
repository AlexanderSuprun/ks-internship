package com.example.ks_internship.screen.webview;

import com.example.ks_internship.base.BasePresenter;
import com.example.ks_internship.base.BaseView;

public interface WebViewContract {

    interface View extends BaseView<Presenter> {
        void loadWebview(String url);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
