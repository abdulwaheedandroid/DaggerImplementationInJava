package com.abdulwaheed.daggerimplementation.models.utilities;

import android.app.Application;

import com.abdulwaheed.daggerimplementation.models.di.components.AppComponents;
import com.abdulwaheed.daggerimplementation.models.di.components.DaggerAppComponents;
import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.models.repositories.sharedpreferences.SharedPreferenceStorage;

public class MyApplication extends Application {

    //Instance of the AppComponent that will be used by all the Activities in the project.

    private AppComponents appComponents =  DaggerAppComponents.factory().create(this);


    public AppComponents getAppComponents() {
        return appComponents;
    }
}
