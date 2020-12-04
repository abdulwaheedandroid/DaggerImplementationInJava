package com.abdulwaheed.daggerimplementation.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abdulwaheed.daggerimplementation.databinding.ActivitySettingsBinding;
import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.models.utilities.MyApplication;
import com.abdulwaheed.daggerimplementation.view_models.SettingsViewModel;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding activitySettingsBinding;

    private SettingsViewModel settingsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activitySettingsBinding.getRoot());

        final UserManager userManager = ((MyApplication) getApplication()).getUserManager();
        settingsViewModel = new SettingsViewModel(userManager.getUserDataRepository(), userManager);

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
