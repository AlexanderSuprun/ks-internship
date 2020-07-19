package com.example.ks_internship.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.fragment.FragmentViewer;
import com.example.ks_internship.model.Cat;
import com.example.ks_internship.utils.Constants;

public class SecondActivity extends BaseActivity {

    private FragmentViewer fragmentViewer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initToolbarWithNavigation(getString(R.string.app_name));

        Cat catToDisplay = getIntent().getParcelableExtra(Constants.KEY_CAT_OBJECT);
        fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.activity_second_fragment_viewer);
        if (catToDisplay != null && fragmentViewer != null) {
            fragmentViewer.displayInformation(catToDisplay);
        }
    }
}
