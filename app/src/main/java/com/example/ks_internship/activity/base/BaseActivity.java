package com.example.ks_internship.activity.base;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.HistoryActivity;
import com.example.ks_internship.app.KsinternshipApp;
import com.example.ks_internship.utils.AppPrefsManager;
import com.example.ks_internship.utils.database.AppDatabase;

/**
 * Base activity abstract class with different toolbar initializations
 */

public abstract class BaseActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;

    public void initToolbar(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    public void initToolbarWithNavigation(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void initToolbarWithClearHistoryAction(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.top_app_bar_clear_history_action);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.toolbar_action_clear_history) {
                    AppPrefsManager.clearHistoryCache(BaseActivity.this);
                    onBackPressed();
                }
                return false;
            }
        });
    }

    public void initToolbarWithHistoryAction(String title) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.inflateMenu(R.menu.top_app_bar_history_action);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.toolbar_action_history) {
                    Intent historyIntent = new Intent(BaseActivity.this, HistoryActivity.class);
                    startActivity(historyIntent);
                }
                return false;
            }
        });

    }

    public AppDatabase getDatabase() {
        return ((KsinternshipApp) getApplication()).getDatabase();
    }
}
