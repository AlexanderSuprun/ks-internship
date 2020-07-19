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
import com.example.ks_internship.utils.listeners.CatSelectListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private FragmentChooser fragmentChooser;
    private FragmentViewer fragmentViewer;

    private CatSelectListener catSelectListener;

    private Map<String, Cat> cats = new HashMap<>();

    private boolean inLandscapeMode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar(getString(R.string.app_name));
        initCats();

        inLandscapeMode = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        fragmentChooser = (FragmentChooser) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_chooser);
        if (inLandscapeMode) {
            fragmentViewer = (FragmentViewer) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_viewer);
        }

        catSelectListener = new CatSelectListener() {
            @Override
            public void onCoconutSelected() {
                displaySelected(cats.get("Coconut"));
            }

            @Override
            public void onMortySelected() {
                displaySelected(cats.get("Morty"));
            }

            @Override
            public void onWoodySelected() {
                displaySelected(cats.get("Woody"));
            }

            @Override
            public void onCakeSelected() {
                displaySelected(cats.get("Cake"));
            }

            @Override
            public void onMarcusSelected() {
                displaySelected(cats.get("Marcus"));
            }
        };
        fragmentChooser.setCatSelectListener(catSelectListener);
    }

    private void displaySelected(Cat cat) {
        if (inLandscapeMode) {
            fragmentViewer.displayInformation(cat);
        } else {
            Intent viewIntent = new Intent(MainActivity.this, SecondActivity.class);
            viewIntent.putExtra(Constants.KEY_CAT_OBJECT, cat);
            startActivity(viewIntent);
        }
    }

    private void initCats() {
        cats.put("Coconut", new Cat("Coconut", "Scottish Fold", "White", "Male", 0.5));
        cats.put("Morty", new Cat("Morty", "No breed", "Brown", "Male", 5));
        cats.put("Woody", new Cat("Woody", "Bengal", "Brown", "Male", 2));
        cats.put("Cake", new Cat("Cake", "Egyptian Mau", "Gray", "Male", 1.5));
        cats.put("Marcus", new Cat("Marcus", "Korat", "Black", "Male", 3));
    }
}