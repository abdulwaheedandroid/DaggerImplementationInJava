package com.abdulwaheed.daggerimplementation.view_models;

import androidx.lifecycle.MutableLiveData;

import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.models.utilities.AppConstants;

import javax.inject.Inject;

public class LoginViewModel {

    private UserManager userManager;
    private MutableLiveData<Byte> loginState = new MutableLiveData<>();

    @Inject
    public LoginViewModel(UserManager userManager) {
        this.userManager = userManager;
    }

    public void attemptLogin(String userName, String password) {
        if (userManager.loginUser(userName, password)) {
            loginState.postValue(AppConstants.LoginStatus.LOGIN_SUCCESS);
        } else {
            loginState.postValue(AppConstants.LoginStatus.LOING_FAIL);
        }
    }

    public MutableLiveData<Byte> getLoginState() {
        return loginState;
    }

    public void unregister() {
        userManager.unRegister();
    }

    public String getUserName() {
        return userManager.getUserName();
    }

}
