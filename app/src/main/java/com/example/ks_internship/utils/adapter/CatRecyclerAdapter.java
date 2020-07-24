package com.example.ks_internship.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ks_internship.R;
import com.example.ks_internship.model.Cat;
import com.example.ks_internship.utils.listeners.OnCatRecyclerItemClickListener;

import java.util.ArrayList;

public class CatRecyclerAdapter extends RecyclerView.Adapter<CatRecyclerAdapter.ViewHolder> {

    private ArrayList<Cat> cats;
    private OnCatRecyclerItemClickListener listener;

    public CatRecyclerAdapter(ArrayList<Cat> cats) {
        this.cats = cats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemTitle.setText(cats.get(position).getName());
        holder.itemDescription.setText(cats.get(position).getBreed());

        if (position == cats.size() - 1) {
            holder.divider.setVisibility(View.INVISIBLE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public void setListener(OnCatRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    public OnCatRecyclerItemClickListener getListener() {
        return listener;
    }

    public void setItems(ArrayList<Cat> cats) {
        this.cats = cats;
    }

    public ArrayList<Cat> getItems() {
        return cats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView itemTitle;
        AppCompatTextView itemDescription;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle = itemView.findViewById(R.id.item_title);
            itemDescription = itemView.findViewById(R.id.item_description);
            divider = itemView.findViewById(R.id.divider);
        }
    }

}
