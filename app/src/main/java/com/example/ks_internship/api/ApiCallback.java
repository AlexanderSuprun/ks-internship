package com.example.ks_internship.api;

import com.example.ks_internship.model.GitRepoError;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    public abstract void success(Response<T> response);
    public abstract void failure(GitRepoError gitRepoError);

    @Override
    public void onResponse(@NotNull Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            Converter<ResponseBody, GitRepoError> converter = RestClient.getInstance()
                    .getRetrofit()
                    .responseBodyConverter(GitRepoError.class, new Annotation[0]);
            try {
                GitRepoError repoError = converter.convert(response.errorBody());
                failure(repoError);
            } catch (IOException e) {
                failure(new GitRepoError("Error: " + response.code()));
            }
        } else {
            success(response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        failure(new GitRepoError("Unexpected error: " + t.getMessage()));
    }
}
