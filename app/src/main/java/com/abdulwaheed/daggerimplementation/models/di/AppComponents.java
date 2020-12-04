package com.abdulwaheed.daggerimplementation.models.di;

import com.abdulwaheed.daggerimplementation.views.activities.RegistrationActivity;

import dagger.Component;

@Component //Definition of a Dagger component
public interface AppComponents {

    //classes that can be injected by this component
    void inject(RegistrationActivity registrationActivity);
}
