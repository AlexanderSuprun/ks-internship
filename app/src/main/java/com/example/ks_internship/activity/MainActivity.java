package com.example.ks_internship.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.fragment.FragmentChooser;
import com.example.ks_internship.fragment.FragmentViewer;
import com.example.ks_internship.model.Cat;
import com.example.ks_internship.utils.Constants;
import com.example.ks_internship.utils.adapter.GitRepoRecyclerAdapter;

import java.util.ArrayList;

/**
 * Fills HashMap with Cat objects.
 * If in portrait mode, contains {@link FragmentChooser} and starts
 * {@link InformationActivity} with {@link FragmentViewer} in it.
 * Contains both {@link FragmentChooser} and {@link FragmentViewer}, if in landscape mode.
 */

public class MainActivity extends BaseActivity {

    private FragmentChooser fragmentChooser;
    private FragmentViewer fragmentViewer;
    private GitRepoRecyclerAdapter adapter;

    private ArrayList<Cat> catArrayList = new ArrayList<>();

    private boolean inLandscapeMode;
    //https://api.github.com/search/repositories?q=user:google
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(getString(R.string.app_name));

        adapter = new GitRepoRecyclerAdapter(catArrayList);
        adapter.setListener(listener);

        inLandscapeMode = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_chooser);

        if (fragmentChooser != null) {
            fragmentChooser.setAdapter(adapter);
        }
        if (inLandscapeMode) {
            fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_viewer);
        }
    }

    private void displaySelected(Cat cat) {
        if (inLandscapeMode) {
            fragmentViewer.displayInformation(cat);
        } else {
            Intent viewIntent = new Intent(MainActivity.this, InformationActivity.class);
            viewIntent.putExtra(Constants.KEY_CAT_OBJECT, cat);
            startActivity(viewIntent);
        }
    }

}