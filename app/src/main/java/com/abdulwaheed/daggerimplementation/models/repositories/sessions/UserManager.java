package com.abdulwaheed.daggerimplementation.models.repositories.sessions;


import com.abdulwaheed.daggerimplementation.models.di.components.UserComponent;
import com.abdulwaheed.daggerimplementation.models.repositories.sharedpreferences.Storage;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Handles User lifecycle. Manages registrations, logs in and logs out.
 * Knows when the user is logged in.
 */
@Singleton
public class UserManager {


    public static final String REGISTERED_USER = "registered_user";
    public static final String PASSWORD_SUFFIX = "password";

    private Storage storage;

    private String mUserName;

    private UserComponent.Factory userComponentFactory;

    private UserComponent userComponent;

    @Inject
    public UserManager(Storage storage,
            /*
             * Since UserManager will be in charge od managing the UserCOmponent lifecycle,
             * it needs to know how to create instances of it.
             * */
        UserComponent.Factory factory) {
        this.userComponentFactory = factory;
        this.storage = storage;
        this.mUserName = storage.getString(REGISTERED_USER);
    }

    public String getUserName() {
        return mUserName;
    }

    public boolean isUserLoggedIn() {
        return userComponent != null;
    }

    public boolean isUserRegistered() {
        return storage.getString(REGISTERED_USER) != null;
    }

    public void registerUser(final String userName, final String password) {
        storage.setString(REGISTERED_USER, userName);
        storage.setString(userName + PASSWORD_SUFFIX, password);
        userJustLoggedIn();
    }

    public boolean loginUser(final String userName, final String password) {
        final String registeredUser = this.mUserName;
        if (!registeredUser.equals(userName)) return false;

        final String registeredPassword = storage.getString(userName + PASSWORD_SUFFIX);
        if (!registeredPassword.equals(password)) return false;

        userJustLoggedIn();
        return true;
    }

    public void logout() {
        userComponent = null;
    }

    public void unRegister() {
        final String userName = storage.getString(REGISTERED_USER);
        storage.setString(REGISTERED_USER, "");
        storage.setString(userName + PASSWORD_SUFFIX, "");
        logout();
    }

    public void userJustLoggedIn() {
        userComponent = userComponentFactory.create();
    }

    public void setUserComponent(UserComponent userComponent) {
        this.userComponent = userComponent;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }
}
