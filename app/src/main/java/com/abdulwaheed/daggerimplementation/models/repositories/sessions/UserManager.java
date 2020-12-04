package com.abdulwaheed.daggerimplementation.models.repositories.sessions;


import com.abdulwaheed.daggerimplementation.models.repositories.sharedpreferences.Storage;

import javax.inject.Inject;

/**
 * Handles User lifecycle. Manages registrations, logs in and logs out.
 * Knows when the user is logged in.
 */
public class UserManager {


    public static final String REGISTERED_USER = "registered_user";
    public static final String PASSWORD_SUFFIX = "password";

    private Storage storage;

    /**
     *  UserDataRepository is specific to a logged in user. This determines if the user
     *  is logged in or not, when the user logs in, a new instance will be created.
     *  When the user logs out, this will be null.
     */

    private UserDataRepository mUserDataRepository;

    private String mUserName;

    @Inject
    public UserManager(Storage storage) {
        this.storage = storage;
        this.mUserName = storage.getString(REGISTERED_USER);
    }

    public String getUserName() {
        return mUserName;
    }

    public boolean isUserLoggedIn() {
        return mUserDataRepository != null;
    }

    public boolean isUserRegistered() {
        return storage.getString(REGISTERED_USER) != null;
    }

    public void registerUser(final String userName, final String password) {
        storage.setString(REGISTERED_USER, userName);
        storage.setString(userName + PASSWORD_SUFFIX , password);
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
        mUserDataRepository = null;
    }

    public void unRegister () {
        final String userName = storage.getString(REGISTERED_USER);
        storage.setString(REGISTERED_USER, "");
        storage.setString(userName + PASSWORD_SUFFIX, "");
        logout();
    }

    public void userJustLoggedIn() {
        mUserDataRepository = new UserDataRepository(this);
    }

    public UserDataRepository getUserDataRepository () {
        return mUserDataRepository;
    }
}
