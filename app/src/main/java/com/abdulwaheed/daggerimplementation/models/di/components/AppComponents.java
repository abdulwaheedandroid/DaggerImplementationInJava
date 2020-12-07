package com.abdulwaheed.daggerimplementation.models.di.components;

import android.content.Context;

import com.abdulwaheed.daggerimplementation.models.di.StorageModule;
import com.abdulwaheed.daggerimplementation.models.repositories.sessions.UserManager;
import com.abdulwaheed.daggerimplementation.views.activities.MainActivity;
import com.abdulwaheed.daggerimplementation.views.activities.RegistrationActivity;
import com.abdulwaheed.daggerimplementation.views.activities.SettingsActivity;
import com.abdulwaheed.daggerimplementation.views.fragments.EnterDetailsFragment;
import com.abdulwaheed.daggerimplementation.views.fragments.TermsAndConditionsFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {StorageModule.class, AppSubComponents.class}) //Definition of a Dagger component
public interface AppComponents {

    @Component.Factory
    interface Factory {
        AppComponents create(@BindsInstance Context context);
    }

    //Expose RegistrationComponent factory from the graph
    RegistrationComponent.Factory registrationComponent();
    LoginComponent.Factory loginComponent();

    /*
    * Expose UserManager so that MainActivity and SettingsActivity can
    * access a particular instance of UserComponent
    * */

    UserManager userManager();

}
