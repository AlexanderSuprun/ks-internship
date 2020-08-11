package com.example.ks_internship.base;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();
}
