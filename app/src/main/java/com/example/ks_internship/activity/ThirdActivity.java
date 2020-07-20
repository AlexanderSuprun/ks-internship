package com.example.ks_internship.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.fragment.FragmentChooser;
import com.example.ks_internship.fragment.FragmentViewer;
import com.example.ks_internship.utils.adapter.ViewPagerAdapter;
import com.example.ks_internship.utils.listeners.CatSelectListener;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import static com.example.ks_internship.activity.MainActivity.cats;

/**
 * Sample of the two fragments and tabs.
 */
public class ThirdActivity extends BaseActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private FragmentChooser fragmentChooser;
    private FragmentViewer fragmentViewer;
    private CatSelectListener catSelectListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initToolbar(getString(R.string.app_name));

        tabLayout = findViewById(R.id.activity_third_sliding_tabs);
        viewPager = findViewById(R.id.activity_third_view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        fragmentChooser = new FragmentChooser();
        fragmentViewer = new FragmentViewer();

        viewPagerAdapter.addFragment(fragmentChooser, "Menu");
        viewPagerAdapter.addFragment(fragmentViewer, "Information");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        implementListeners();
    }

    private void implementListeners() {
            catSelectListener = new CatSelectListener() {
                @Override
                public void onCoconutSelected() {
                    fragmentViewer.displayInformation(Objects.requireNonNull(cats.get("Coconut")));
                    viewPager.setCurrentItem(2, true);
                }

                @Override
                public void onMortySelected() {
                    fragmentViewer.displayInformation(Objects.requireNonNull(cats.get("Morty")));
                    viewPager.setCurrentItem(2, true);
                }

                @Override
                public void onWoodySelected() {
                    fragmentViewer.displayInformation(Objects.requireNonNull(cats.get("Woody")));
                    viewPager.setCurrentItem(2, true);
                }

                @Override
                public void onCakeSelected() {
                    fragmentViewer.displayInformation(Objects.requireNonNull(cats.get("Cake")));
                    viewPager.setCurrentItem(2, true);
                }

                @Override
                public void onMarcusSelected() {
                    fragmentViewer.displayInformation(Objects.requireNonNull(cats.get("Marcus")));
                    viewPager.setCurrentItem(2, true);
                }
            };
            fragmentChooser.setCatSelectListener(catSelectListener);
    }
}
