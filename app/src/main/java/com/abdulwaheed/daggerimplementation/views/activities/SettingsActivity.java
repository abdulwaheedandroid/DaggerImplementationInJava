package com.abdulwaheed.daggerimplementation.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abdulwaheed.daggerimplementation.databinding.ActivitySettingsBinding;
import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.models.utilities.MyApplication;
import com.abdulwaheed.daggerimplementation.view_models.SettingsViewModel;

import javax.inject.Inject;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding activitySettingsBinding;

    @Inject // 1) SettingViewModel is provided by Dagger
    SettingsViewModel settingsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        /*
        * Gets the UserManager from the application graph to obtain the instance
        * of UserComponent and gets this Activity injected
        * */

        UserManager userManager = ((MyApplication) getApplication()).getAppComponents().userManager();
        userManager.getUserComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(activitySettingsBinding.getRoot());

        setListeners();
    }

    private void setListeners() {
        activitySettingsBinding.refresh.setOnClickListener(v -> {
            settingsViewModel.refreshNotifications();
        });

        activitySettingsBinding.logout.setOnClickListener( v -> {
            settingsViewModel.logout();
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK|
                            Intent.FLAG_ACTIVITY_NEW_TASK
            );
            startActivity(intent);
        });
    }
}
