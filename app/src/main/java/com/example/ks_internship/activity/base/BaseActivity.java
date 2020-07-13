package com.example.ks_internship.activity.base;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ks_internship.R;

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
        toolbar.inflateMenu(R.menu.top_app_bar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_favorite:
                        showToast(getString(R.string.toast_favorite_pressed));
                        break;
                    case R.id.toolbar_action_first:
                        showToast(getString(R.string.toast_action_first_pressed));
                        break;
                    case R.id.toolbar_action_second:
                        showToast(getString(R.string.toast_action_second_pressed));
                        break;
                    case R.id.toolbar_action_third:
                        showToast(getString(R.string.toast_action_third_pressed));
                }
                return true;
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
