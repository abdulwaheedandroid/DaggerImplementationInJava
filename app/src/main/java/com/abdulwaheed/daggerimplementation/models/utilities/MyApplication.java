package com.abdulwaheed.daggerimplementation.models.utilities;

import android.app.Application;

import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.models.repositories.sharedpreferences.SharedPreferenceStorage;

public class MyApplication extends Application {

    private UserManager userManager;

    @Override
    public void onCreate() {
        super.onCreate();
        userManager = new UserManager(new SharedPreferenceStorage(getApplicationContext()));
    }

    public UserManager getUserManager(){
        return userManager;
    }



}
