package com.example.ks_internship.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.base.BaseActivity;
import com.example.ks_internship.screen.main.MainContract;
import com.example.ks_internship.screen.main.MainFragment;
import com.example.ks_internship.screen.main.MainPresenter;
import com.example.ks_internship.utils.AppPrefsManager;
import com.example.ks_internship.utils.Constants;


public class MainActivity extends BaseActivity {

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainContract.Presenter presenter;
        fragmentContainer = findViewById(R.id.fragment_main_container);

        AppPrefsManager prefsManager = new AppPrefsManager(getApplicationContext());
        MainFragment mainFragment = new MainFragment();

        if (getIntent().getStringExtra(Constants.HISTORY_TITLE) != null) {
            mainFragment = MainFragment.newInstance(getIntent().getStringExtra(Constants.HISTORY_TITLE));
        }

        presenter = new MainPresenter(prefsManager, getDatabase());

        if (!getSupportFragmentManager().getFragments().isEmpty()) {
            mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(fragmentContainer.getId());
        } else {
            getSupportFragmentManager().beginTransaction().add(fragmentContainer.getId(), mainFragment).commit();
        }

        if (mainFragment != null) {
            mainFragment.setPresenter(presenter);
        }
    }

}