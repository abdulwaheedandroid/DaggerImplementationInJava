package com.abdulwaheed.daggerimplementation.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abdulwaheed.daggerimplementation.R;
import com.abdulwaheed.daggerimplementation.databinding.ActivityRegistrationBinding;
import com.abdulwaheed.daggerimplementation.models.di.components.RegistrationComponent;
import com.abdulwaheed.daggerimplementation.models.utilities.MyApplication;
import com.abdulwaheed.daggerimplementation.view_models.RegistrationViewModel;
import com.abdulwaheed.daggerimplementation.views.fragments.EnterDetailsFragment;
import com.abdulwaheed.daggerimplementation.views.fragments.TermsAndConditionsFragment;

import javax.inject.Inject;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding mActivityRegistrationBinding;

    @Inject //@Inject annotated fields will be provided by Dagger
    RegistrationViewModel registrationViewModel;

    //Stores an instance of RegistrationComponent so that its Fragments can access it
    public RegistrationComponent registrationComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Create an instance of Registration component by grabbing the factory from the app graph
        registrationComponent = ((MyApplication) getApplication()).getAppComponents().registrationComponent().create();

        //Injects this activity to the just created registration component
        registrationComponent.inject(this);
        super.onCreate(savedInstanceState);
        //Binding View
        mActivityRegistrationBinding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(mActivityRegistrationBinding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_holder, new EnterDetailsFragment())
                .commit();
    }

    /**
     * Callback from EnterDetailsFragment when username and password has been entered
     */

    public void onDetailsEntered() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_holder, new TermsAndConditionsFragment())
                .addToBackStack(TermsAndConditionsFragment.class.getSimpleName())
                .commit();
    }

    /**
     * Callback from T&CsFragment when TCs have been accepted
     */

    public void onTermsAndConditionsAccepted() {
        registrationViewModel.registerUser();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public RegistrationViewModel getRegistrationViewModel() {
        return registrationViewModel;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else super.onBackPressed();
    }
}
