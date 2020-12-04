package com.abdulwaheed.daggerimplementation.view_models;

import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserDataRepository;
import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;

/**
 * SettingsViewModel is the ViewModel that [SettingsActivity] uses to handle complex logic.
 */
public class SettingsViewModel {

    private UserDataRepository userDataRepository;
    private UserManager userManager;

    public SettingsViewModel(UserDataRepository userDataRepository, UserManager userManager) {
        this.userDataRepository = userDataRepository;
        this.userManager = userManager;
    }

    public void refreshNotifications() {
        userDataRepository.refreshUnreadNotification();
    }

    public void logout() {
        userManager.logout();
    }
}
