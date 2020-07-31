package com.example.ks_internship.utils.listeners;

import android.net.Uri;
import android.view.View;

public interface OnGitRepoRecyclerItemClickListener {
    void onItemClick (View v, int position, Uri url);
}
