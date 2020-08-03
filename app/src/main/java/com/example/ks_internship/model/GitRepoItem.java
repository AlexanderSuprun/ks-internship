package com.example.ks_internship.model;

import android.net.Uri;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ks_internship.utils.database.UriConverter;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "gitRepoItems")
@TypeConverters({UriConverter.class})
public class GitRepoItem {

    @PrimaryKey(autoGenerate = true)
    int id;
    @SerializedName("html_url")
    private Uri url;
    private String name;
    private String description;
    @Embedded
    private GitRepoOwner owner;

    public GitRepoItem(int id, Uri url, String name, String description, GitRepoOwner owner) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

        return id == that.id &&
                url.equals(that.url) &&
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
        result = 31 * result + id;
        return result;
    }
}
