package com.example.ks_internship.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.fragment.FragmentOne;
import com.example.ks_internship.fragment.FragmentTwo;

/**
 * Sample of {@link androidx.fragment.app.FragmentManager} and
 * {@link FragmentTransaction} usage.
 */

public class FourthActivity extends BaseActivity {

    private AppCompatButton btnAdd;
    private AppCompatButton btnRemove;
    private AppCompatButton btnReplace;

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        initToolbar(getString(R.string.app_name));
        initViews();

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

        setListeners();
    }

    private void initViews() {
        btnAdd = findViewById(R.id.activity_fourth_btn_add);
        btnRemove = findViewById(R.id.activity_fourth_btn_remove);
        btnReplace = findViewById(R.id.activity_fourth_btn_replace);
    }

    private void setListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(fragmentOne);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment(fragmentOne);
            }
        });

        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(fragmentTwo);
            }
        });
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.activity_fourth_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment)
                .addToBackStack(null)
                .commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_fourth_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
