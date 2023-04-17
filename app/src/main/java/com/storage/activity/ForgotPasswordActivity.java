package com.storage.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.storage.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputLayout input_1;
    private TextInputLayout input_2;
    private TextInputLayout input_3;
    private TextInputLayout input_4;
    private TextInputLayout input_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

//        setTransparentStatusBar();
        init();
    }

    private void init() {
        input_1 = findViewById(R.id.input_1);
        input_2 = findViewById(R.id.input_2);
        input_3 = findViewById(R.id.input_3);
        input_4 = findViewById(R.id.input_4);
        input_5 = findViewById(R.id.input_5);

        input_1.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (input_1.getEditText().getText().length() == 1) input_2.requestFocus();
            }
        });

        input_2.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (input_2.getEditText().getText().length() == 1) input_3.requestFocus();
                else input_1.requestFocus();
            }
        });

        input_3.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (input_3.getEditText().getText().length() == 1) input_4.requestFocus();
                else input_2.requestFocus();
            }
        });

        input_4.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (input_4.getEditText().getText().length() == 1) input_5.requestFocus();
                else input_3.requestFocus();
            }
        });

        input_5.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (input_5.getEditText().getText().length() == 1) input_5.clearFocus();
                else input_4.requestFocus();
            }
        });
    }

    private void setTransparentStatusBar() {
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}