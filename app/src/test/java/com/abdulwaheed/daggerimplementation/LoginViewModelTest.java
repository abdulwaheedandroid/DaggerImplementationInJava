package com.abdulwaheed.daggerimplementation;

import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.view_models.LoginViewModel;

import org.junit.Before;
import org.junit.Test;

public class LoginViewModelTest {

    private LoginViewModel loginViewModel;
    private UserManager userManager;

    @Before
    public void setUp() {
        //User manager instance should be provided here.
        loginViewModel = new LoginViewModel(userManager);
    }


}
