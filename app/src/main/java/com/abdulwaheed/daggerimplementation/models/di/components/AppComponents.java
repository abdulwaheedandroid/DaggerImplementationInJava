package com.abdulwaheed.daggerimplementation.models.di.components;

import android.content.Context;

import com.abdulwaheed.daggerimplementation.models.di.StorageModule;
import com.abdulwaheed.daggerimplementation.views.activities.MainActivity;
import com.abdulwaheed.daggerimplementation.views.activities.RegistrationActivity;
import com.abdulwaheed.daggerimplementation.views.fragments.EnterDetailsFragment;
import com.abdulwaheed.daggerimplementation.views.fragments.TermsAndConditionsFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = StorageModule.class) //Definition of a Dagger component
public interface AppComponents {

    //Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        //With @BindInstance, the Context passed in will be available in the graph
        AppComponents create(@BindsInstance Context context);
    }
    //classes that can be injected by this component
    void inject(RegistrationActivity registrationActivity);
    void inject(MainActivity activity);
    void inject(EnterDetailsFragment enterDetailsFragment);
    void inject(TermsAndConditionsFragment termsAndConditionsFragment);
}
