package com.example.ks_internship.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ks_internship.R;
import com.example.ks_internship.utils.listeners.OnHistoryRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    ArrayList<String> historyItems;
    OnHistoryRecyclerItemClickListener listener;

    public HistoryRecyclerAdapter(List<String> items, OnHistoryRecyclerItemClickListener listener) {
        this.historyItems = (ArrayList<String>) items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecyclerAdapter.ViewHolder holder, int position) {

        holder.title.setText(historyItems.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, holder.getAdapterPosition(), historyItems.get(holder.getAdapterPosition()));
                }
            }
        });

        if (position == historyItems.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView title;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.history_item_title);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}
