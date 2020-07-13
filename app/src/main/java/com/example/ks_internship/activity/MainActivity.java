package com.example.ks_internship.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.ks_internship.R;
import com.example.ks_internship.activity.base.BaseActivity;
import com.example.ks_internship.utils.Constants;

public class MainActivity extends BaseActivity {

    private AppCompatEditText editText;
    private AppCompatButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
        initToolbar(getString(R.string.app_name));
    }

    private void initViews() {
        editText = findViewById(R.id.activity_main_edit_text);
        btnSend = findViewById(R.id.activity_main_btn_send);
    }

    private void setListeners() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondActivityForResult();
            }
        });
    }

    private void openSecondActivityForResult() {
        if (TextUtils.isEmpty(editText.getText())) {
            Toast.makeText(MainActivity.this,
                    getString(R.string.toast_empty_text_field_text), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(Constants.EXTRA_MESSAGE, editText.getText().toString());
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this,
                        getString(R.string.toast_success_text), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                editText.setText("");
            }
        }
    }
}