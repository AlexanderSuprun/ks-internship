package com.example.ks_internship.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class GitRepoItem {

    @SerializedName("html_url")
    private Uri url;
    private String name;
    private String description;
    private GitRepoOwner owner;

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GitRepoOwner getOwner() {
        return owner;
    }

    public void setOwner(GitRepoOwner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitRepoItem that = (GitRepoItem) o;

        return url.equals(that.url) &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                owner.equals(that.owner);
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
