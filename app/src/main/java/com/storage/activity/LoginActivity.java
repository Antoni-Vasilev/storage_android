package com.storage.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.storage.R;
import com.storage.functions.Database;
import com.storage.functions.Navigation;
import com.storage.model.ExceptionMessage;
import com.storage.model.TokenPublic;
import com.storage.model.UserLoginDto;
import com.storage.retrofit.RetrofitService;
import com.storage.retrofit.v1.UserController;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout fieldEmail;
    private TextInputLayout fieldPassword;
    private RelativeLayout btnLogin;
    private TextView btnLoginText;
    private ProgressBar btnLoginProgress;
    private LinearLayout btnRegister;

    private int btnWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTransparentStatusBar();
        init();

        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            btnWidth = btnLogin.getMeasuredWidth();
        }).start();
    }

    private void setTransparentStatusBar() {
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    private void init() {
        fieldEmail = findViewById(R.id.fieldEmail);
        fieldPassword = findViewById(R.id.fieldPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginText = findViewById(R.id.btnLoginText);
        btnLoginProgress = findViewById(R.id.btnLoginProgress);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(it -> login());
        btnRegister.setOnClickListener(it -> Navigation.toRegister(this, Navigation.NONE));
    }

    private void login() {
        String email = readField(fieldEmail);
        String password = readField(fieldPassword);

        UserController userController = RetrofitService.retrofit.create(UserController.class);

        startButtonAnimation();

        @SuppressLint("HardwareIds") UserLoginDto userLoginDto = new UserLoginDto(email, password, Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
        userController.login(userLoginDto)
                .enqueue(new Callback<TokenPublic>() {
                    @Override
                    public void onResponse(@NonNull Call<TokenPublic> call, @NonNull Response<TokenPublic> response) {
                        if (response.code() != 200) {
                            stopButtonAnimation();

                            try {
                                assert response.errorBody() != null;
                                String errorBody = response.errorBody().string();
                                Gson gson = new Gson();
                                ExceptionMessage exceptionMessage = gson.fromJson(errorBody, ExceptionMessage.class);

                                Toast.makeText(LoginActivity.this, exceptionMessage.getMessage(), Toast.LENGTH_SHORT).show();

                                if (exceptionMessage.getErrors() != null) {
                                    for (ExceptionMessage e : exceptionMessage.getErrors()) {
                                        if (e.getTitle().equals("email")) {
                                            fieldEmail.setError(e.getMessage());
                                        } else if (e.getTitle().equals("password")) {
                                            fieldPassword.setError(e.getMessage());
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            assert response.body() != null;
                            Database.Edit.putString(Database.Keys.token.val, response.body().getToken());
                            Navigation.toHome(LoginActivity.this, Navigation.ALL);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<TokenPublic> call, @NonNull Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        stopButtonAnimation();
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void startButtonAnimation() {
        @SuppressLint("Recycle") ValueAnimator anim = ValueAnimator.ofInt(btnLogin.getMeasuredWidth(), (int) getResources().getDimension(R.dimen.login_button_anim_width));
        anim.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = btnLogin.getLayoutParams();
            layoutParams.width = value;
            btnLogin.requestLayout();
        });
        anim.setDuration(250);
        anim.start();

        btnLoginText.setVisibility(View.GONE);
        btnLoginProgress.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void stopButtonAnimation() {
        @SuppressLint("Recycle") ValueAnimator anim = ValueAnimator.ofInt(btnLogin.getMeasuredWidth(), btnWidth);
        anim.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            ViewGroup.LayoutParams layoutParams = btnLogin.getLayoutParams();
            layoutParams.width = value;
            btnLogin.requestLayout();
        });
        anim.setDuration(250);
        anim.start();

        btnLoginText.setVisibility(View.VISIBLE);
        btnLoginProgress.setVisibility(View.GONE);
    }

    private String readField(TextInputLayout field) {
        if (field.getEditText() != null) return field.getEditText().getText().toString().trim();
        else return null;
    }
}