package com.abdulwaheed.daggerimplementation.view_models;

import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserDataRepository;

public class MainViewModel {
    private UserDataRepository userDataRepository;

    private String welcomeText, notificationText;

    public MainViewModel(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public String getWelcomeText() {
        welcomeText = "Hello " + userDataRepository.getUserName() + " !";
        return welcomeText;
    }

    public String getNotificationText() {
        notificationText = "You have " + userDataRepository.getUnreadNotificationCount() + " unread notifications!";
        return notificationText;
    }
}
