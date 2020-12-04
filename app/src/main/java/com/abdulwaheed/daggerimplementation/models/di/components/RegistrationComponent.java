package com.abdulwaheed.daggerimplementation.models.di.components;

import com.abdulwaheed.daggerimplementation.views.activities.RegistrationActivity;
import com.abdulwaheed.daggerimplementation.views.fragments.EnterDetailsFragment;
import com.abdulwaheed.daggerimplementation.views.fragments.TermsAndConditionsFragment;

import dagger.Subcomponent;

//Definition of a Dagger sub component
@Subcomponent
public interface RegistrationComponent {

    //Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        RegistrationComponent create();
    }

    //Classes that can be injected by this Component
    void inject(RegistrationActivity registrationActivity);
    void inject(EnterDetailsFragment enterDetailsFragment);
    void inject(TermsAndConditionsFragment termsAndConditionsFragment);
}
