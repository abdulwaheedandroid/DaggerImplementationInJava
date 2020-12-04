package com.abdulwaheed.daggerimplementation.view_models;

import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;

public class RegistrationViewModel {

    private UserManager userManager;

    public RegistrationViewModel(UserManager userManager) {
        this.userManager = userManager;
    }

    private String userName, password;
    private boolean isTermAndConditionAccepted;

    public void updateUserData (final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    public void acceptTermAndConditions() {
        isTermAndConditionAccepted = true;
    }

    public void registerUser() {
        assert (userName != null);
        assert (password != null);
        assert (isTermAndConditionAccepted == true);

        userManager.registerUser(userName, password);
    }
    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTermAndConditionAccepted() {
        return isTermAndConditionAccepted;
    }

    public void setTermAndConditionAccepted(boolean termAndConditionAccepted) {
        isTermAndConditionAccepted = termAndConditionAccepted;
    }
}
