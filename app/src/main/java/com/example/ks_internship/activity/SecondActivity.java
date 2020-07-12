package com.example.ks_internship.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.ks_internship.R;
import com.example.ks_internship.utils.Constants;

public class SecondActivity extends AppCompatActivity {

    private AppCompatTextView textView;
    private AppCompatButton btnOk;
    private AppCompatButton btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initViews();
        setListeners();
        if (getIntent().getExtras() != null) {
            String message = getIntent().getStringExtra(Constants.EXTRA_MESSAGE);
            textView.setText(message);
        }
    }

    private void initViews() {
        textView = findViewById(R.id.activity_second_textview);
        btnOk = findViewById(R.id.activity_second_btn_ok);
        btnCancel = findViewById(R.id.activity_second_btn_cancel);
    }

    private void setListeners() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
