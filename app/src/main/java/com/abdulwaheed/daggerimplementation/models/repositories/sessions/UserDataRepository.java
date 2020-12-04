package com.abdulwaheed.daggerimplementation.models.repositories.sessions;

import java.util.Random;

import javax.inject.Inject;

/**
 * UserDataRepository contains user-specific data such as username and unread notifications.
 */
public class UserDataRepository {

    private UserManager mUserManager;

    private int mUnreadNotification;

    @Inject
    public UserDataRepository(UserManager userManager) {
        mUserManager = userManager;
        mUnreadNotification = getRandomNumber();
    }

    public String getUserName() {
        return mUserManager.getUserName();
    }

    private int getRandomNumber() {
        return new Random().nextInt(100);
    }

    public void refreshUnreadNotification() {
        mUnreadNotification = getRandomNumber();
    }

    public int getUnreadNotificationCount() {
        return mUnreadNotification;
    }

}
