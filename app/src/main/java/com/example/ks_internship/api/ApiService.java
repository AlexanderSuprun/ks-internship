package com.example.ks_internship.api;

import com.example.ks_internship.model.GitRepoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/users/{user}/repos")
    Call<List<GitRepoItem>> searchReposByUsername(@Path(value = "user", encoded = false) String user);
}
