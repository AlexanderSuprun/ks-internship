package com.example.ks_internship.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.model.Cat;
import com.example.ks_internship.utils.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.example.ks_internship.fragment.FragmentChooser;

/**
 * Checks all entered data and
 * adds new {@link Cat} to {@link FragmentChooser}.
 */
public class NewCatActivity extends BaseActivity {

    private TextInputEditText editTextName;
    private TextInputEditText editTextBreed;
    private TextInputEditText editTextColor;
    private TextInputEditText editTextGender;
    private TextInputEditText editTextAge;
    private AppCompatButton btnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cat);

        initToolbar(getString(R.string.app_name));
        initViews();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCat();
            }
        });
    }

    private void initViews() {
        editTextName = findViewById(R.id.activity_new_cat_layout_input_name);
        editTextBreed = findViewById(R.id.activity_new_cat_layout_input_breed);
        editTextColor = findViewById(R.id.activity_new_cat_layout_input_color);
        editTextGender = findViewById(R.id.activity_new_cat_layout_input_gender);
        editTextAge = findViewById(R.id.activity_new_cat_layout_input_age);
        btnAdd = findViewById(R.id.activity_new_cat_btn_add);
    }

    private void addNewCat() {
        if (TextUtils.isEmpty(editTextName.getText()) || TextUtils.isEmpty(editTextBreed.getText()) ||
            TextUtils.isEmpty(editTextColor.getText()) || TextUtils.isEmpty(editTextGender.getText()) ||
            TextUtils.isEmpty(editTextAge.getText())) {

            new AlertDialog.Builder(NewCatActivity.this)
                    .setTitle("Error")
                    .setMessage("Please fill in all fields")
                    .setCancelable(true)
                    .create().show();
        } else {
            Cat newCat = new Cat(editTextName.getText().toString(), editTextBreed.getText().toString(),
                    editTextColor.getText().toString(), editTextGender.getText().toString(),
                    Float.parseFloat(editTextAge.getText().toString()));

            Intent intent = new Intent();
            intent.putExtra(Constants.KEY_CAT_OBJECT, newCat);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
