package com.example.ks_internship.utils.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ks_internship.R;
import com.example.ks_internship.model.GitRepoItem;
import com.example.ks_internship.utils.listener.OnGitRepoRecyclerItemClickListener;

import java.util.ArrayList;

public class GitRepoRecyclerAdapter extends RecyclerView.Adapter<GitRepoRecyclerAdapter.ViewHolder> {

    private OnGitRepoRecyclerItemClickListener listener;
    private ArrayList<GitRepoItem> gitRepoItems;

    public GitRepoRecyclerAdapter(ArrayList<GitRepoItem> gitRepoItems, OnGitRepoRecyclerItemClickListener listener) {
        this.gitRepoItems = gitRepoItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GitRepoRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitRepoRecyclerAdapter.ViewHolder holder, int position) {
        String description = gitRepoItems.get(position).getDescription();

        if (TextUtils.isEmpty(description)) {
            holder.repoDescription.setVisibility(View.GONE);
        } else {
            holder.repoDescription.setText(description);
            holder.repoDescription.setVisibility(View.VISIBLE);
        }

        holder.repoName.setText(gitRepoItems.get(position).getName());
        Glide.with(holder.repoAvatar).load(gitRepoItems
                .get(position)
                .getOwner()
                .getAvatarURL())
                .placeholder(R.drawable.ic_photo_black_24dp)
                .into(holder.repoAvatar);

        if (position == gitRepoItems.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, holder.getAdapterPosition(), gitRepoItems.get(holder.getAdapterPosition()).getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gitRepoItems.size();
    }

    public ArrayList<GitRepoItem> getGitRepoItems() {
        return gitRepoItems;
    }

    public void setGitRepoItems(ArrayList<GitRepoItem> gitRepoItems) {
        this.gitRepoItems = gitRepoItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView repoName;
        AppCompatTextView repoDescription;
        AppCompatImageView repoAvatar;
        View divider;
        View container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            repoName = itemView.findViewById(R.id.repo_list_item_tv_repo_name);
            repoDescription = itemView.findViewById(R.id.repo_list_item_tv_repo_description);
            repoAvatar = itemView.findViewById(R.id.repo_list_item_iv_avatar);
            divider = itemView.findViewById(R.id.divider);
            container = itemView;
        }
    }

}
