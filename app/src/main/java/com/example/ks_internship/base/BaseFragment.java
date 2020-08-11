package com.example.ks_internship.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.HistoryActivity;
import com.example.ks_internship.utils.AppPrefsManager;

public abstract class BaseFragment extends Fragment {

    private androidx.appcompat.widget.Toolbar toolbar;

    public void initToolbar(String title, View parentView) {
        toolbar = parentView.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    public void initToolbarWithNavigation(String title, View parentView) {
        toolbar = parentView.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    public void initToolbarWithClearHistoryAction(String title, View parentView) {
        toolbar = parentView.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.top_app_bar_clear_history_action);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.toolbar_action_clear_history) {
                    AppPrefsManager.clearHistoryCache(getActivity());
                    getActivity().onBackPressed();
                }
                return false;
            }
        });
    }

    public void initToolbarWithHistoryAction(String title, View parentView) {
        toolbar = parentView.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.inflateMenu(R.menu.top_app_bar_history_action);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.toolbar_action_history) {
                    Intent historyIntent = new Intent(getActivity(), HistoryActivity.class);
                    startActivity(historyIntent);
                }
                return false;
            }
        });

    }
}
