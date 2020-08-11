package com.example.ks_internship.base;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ks_internship.app.KsinternshipApp;
import com.example.ks_internship.utils.database.AppDatabase;

/**
 * Base activity abstract class with different toolbar initializations
 */

public abstract class BaseActivity extends AppCompatActivity {

    public AppDatabase getDatabase() {
        return ((KsinternshipApp) getApplication()).getDatabase();
    }
}
