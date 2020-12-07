package com.abdulwaheed.daggerimplementation.models.di.components;

import com.abdulwaheed.daggerimplementation.views.activities.LoginActivity;

import dagger.Subcomponent;

/*
* Scope annotation that the LoginComponent uses Classes
* annotated with @ActivityScope will have a unique instance in this component
* */
@ActivityScope
//Definition of a Dagger sub component
@Subcomponent
public interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        LoginComponent create();
    }

    //Classes that can be injected by this Component
    void inject(LoginActivity activity);
}
