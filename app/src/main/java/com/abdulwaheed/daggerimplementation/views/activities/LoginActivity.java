package com.abdulwaheed.daggerimplementation.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abdulwaheed.daggerimplementation.databinding.ActivityLoginBinding;
import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.models.utilities.AppConstants;
import com.abdulwaheed.daggerimplementation.models.utilities.MyApplication;
import com.abdulwaheed.daggerimplementation.view_models.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View Binding
        mActivityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(mActivityLoginBinding.getRoot());
        initInstanceVariables();
        setListeners();

        mLoginViewModel.getLoginState().observe(this, loginState -> {
            switch (loginState) {
                case AppConstants.LoginStatus.LOGIN_SUCCESS:
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    break;

                case AppConstants.LoginStatus.LOING_FAIL:
                    mActivityLoginBinding.error.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }

    private void setListeners() {
        mActivityLoginBinding.login.setOnClickListener(v -> {
            mLoginViewModel.attemptLogin(
                    mActivityLoginBinding.username.getText().toString(),
                    mActivityLoginBinding.password.getText().toString()
            );
        });

        mActivityLoginBinding.unregister.setOnClickListener(v -> {
            mLoginViewModel.unregister();
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK|
                            Intent.FLAG_ACTIVITY_NEW_TASK
            );
            startActivity(intent);
        });
    }

    private void initInstanceVariables() {
        mLoginViewModel = new LoginViewModel(((MyApplication) getApplication()) .getUserManager());

    }
}
