package com.example.ks_internship.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;


public class GitRepoOwner {

    private String login;

    @SerializedName("avatar_url")
    private Uri avatarURL;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Uri getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(Uri avatarURL) {
        this.avatarURL = avatarURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitRepoOwner that = (GitRepoOwner) o;
        return login.equals(that.login) &&
                avatarURL.equals(that.avatarURL);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result +(login != null ? login.hashCode() : 0);
        result = 31 * result + (avatarURL != null ? avatarURL.hashCode() : 0);
        return result;
    }
}
