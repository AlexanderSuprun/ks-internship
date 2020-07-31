package com.example.ks_internship.model;

import com.google.gson.annotations.SerializedName;

public class GitRepoError {

    private String message;
    @SerializedName("documentation_url")
    private String docURL;

    public GitRepoError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocURL() {
        return docURL;
    }

    public void setDocURL(String docURL) {
        this.docURL = docURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitRepoError that = (GitRepoError) o;

        return docURL.equals(that.docURL) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        int result = message.hashCode();
        result = 31 * result + docURL.hashCode();
        return result;
    }
}
