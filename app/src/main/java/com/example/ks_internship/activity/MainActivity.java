package com.example.ks_internship.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.fragment.FragmentChooser;
import com.example.ks_internship.fragment.FragmentViewer;
import com.example.ks_internship.model.Cat;
import com.example.ks_internship.utils.Constants;
import com.example.ks_internship.utils.listeners.OnCatRecyclerItemClickListener;

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

    private ArrayList<Cat> catArrayList = new ArrayList<>();

    private boolean inLandscapeMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(getString(R.string.app_name));
        initCats();

        inLandscapeMode = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_chooser);
        if (fragmentChooser != null) {
            fragmentChooser.initAdapter(catArrayList, listener);
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

    private OnCatRecyclerItemClickListener listener = new OnCatRecyclerItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            displaySelected(catArrayList.get(position));
        }
    };

    private void initCats() {
        catArrayList.add(new Cat("Coconut", "Scottish Fold", "White", "Male", 0.5));
        catArrayList.add(new Cat("Morty", "No breed", "Brown", "Male", 5));
        catArrayList.add(new Cat("Woody", "Bengal", "Brown", "Male", 2));
        catArrayList.add(new Cat("Cake", "Egyptian Mau", "Gray", "Male", 1.5));
        catArrayList.add(new Cat("Marcus", "Korat", "Black", "Male", 3));
    }
}