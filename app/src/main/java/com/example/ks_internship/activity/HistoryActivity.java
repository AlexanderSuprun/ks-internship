package com.example.ks_internship.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.base.BaseActivity;
import com.example.ks_internship.screen.history.HistoryContract;
import com.example.ks_internship.screen.history.HistoryFragment;
import com.example.ks_internship.screen.history.HistoryPresenter;
import com.example.ks_internship.utils.AppPrefsManager;

/**
 * Contains search history
 */

public class HistoryActivity extends BaseActivity {

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        HistoryContract.Presenter presenter;
        AppPrefsManager prefsManager = new AppPrefsManager(getApplicationContext());
        fragmentContainer = findViewById(R.id.fragment_container);
        presenter = new HistoryPresenter(prefsManager);

        HistoryFragment historyFragment = new HistoryFragment();

        if (!getSupportFragmentManager().getFragments().isEmpty()) {
            historyFragment = (HistoryFragment) getSupportFragmentManager().findFragmentById(fragmentContainer.getId());
        } else {
            getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), historyFragment).commit();
        }

        if (historyFragment != null) {
            historyFragment.setPresenter(presenter);
        }

    }
}
