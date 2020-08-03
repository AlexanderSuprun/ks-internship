package com.example.ks_internship.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.utils.AppPrefsManager;
import com.example.ks_internship.utils.Constants;
import com.example.ks_internship.utils.adapter.HistoryRecyclerAdapter;
import com.example.ks_internship.utils.listeners.OnHistoryRecyclerItemClickListener;


import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity {

    RecyclerView recyclerView;
    HistoryRecyclerAdapter adapter;
    ArrayList<String> historyItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initToolbarWithNavigation(getString(R.string.top_bar_title_search_history));
        recyclerView = findViewById(R.id.activity_history_rv);
        historyItems = (ArrayList<String>) AppPrefsManager.getCachedSearchHistory(this);

        adapter = new HistoryRecyclerAdapter(historyItems, new OnHistoryRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String name) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                intent.putExtra(Constants.HISTORY_TITLE, historyItems.get(position));
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
