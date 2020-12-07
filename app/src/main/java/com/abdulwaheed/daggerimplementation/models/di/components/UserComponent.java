package com.abdulwaheed.daggerimplementation.models.di.components;

import com.abdulwaheed.daggerimplementation.views.activities.MainActivity;
import com.abdulwaheed.daggerimplementation.views.activities.SettingsActivity;

import dagger.Subcomponent;

@LoggedUserScope
//Definition of a Dagger subcomponent
@Subcomponent
public interface UserComponent {

    //Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        UserComponent create();
    }

    //Classes that can be injected by this Component

    void inject(MainActivity activity);
    void inject(SettingsActivity activity);
}
