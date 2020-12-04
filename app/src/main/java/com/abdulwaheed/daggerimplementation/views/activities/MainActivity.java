package com.abdulwaheed.daggerimplementation.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abdulwaheed.daggerimplementation.R;
import com.abdulwaheed.daggerimplementation.databinding.ActivityMainBinding;
import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.models.utilities.MyApplication;
import com.abdulwaheed.daggerimplementation.view_models.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject //@Inject annotated fields will be provided by Dagger
    MainViewModel mainViewModel;

    @Inject
    UserManager userManager;

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);


        /**
         * If the User is not registered, RegistrationActivity will be launched,
         * If the User is not logged in, LoginActivity will be launched,
         * else carry on with MainActivity
         */

        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                startActivity(new Intent(this, RegistrationActivity.class));
                finish();
            } else {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        } else {
            setContentView(activityMainBinding.getRoot());
            initInstanceVariables();
            setListeners();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityMainBinding.notifications.setText(mainViewModel.getNotificationText());
    }

    private void setListeners() {
        activityMainBinding.settings.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    private void initInstanceVariables() {
        activityMainBinding.hello.setText(mainViewModel.getWelcomeText());
    }
}