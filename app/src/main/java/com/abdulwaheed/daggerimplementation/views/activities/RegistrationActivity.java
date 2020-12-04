package com.abdulwaheed.daggerimplementation.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abdulwaheed.daggerimplementation.R;
import com.abdulwaheed.daggerimplementation.databinding.ActivityRegistrationBinding;
import com.abdulwaheed.daggerimplementation.models.utilities.MyApplication;
import com.abdulwaheed.daggerimplementation.view_models.RegistrationViewModel;
import com.abdulwaheed.daggerimplementation.views.fragments.EnterDetailsFragment;
import com.abdulwaheed.daggerimplementation.views.fragments.TermsAndConditionsFragment;

import javax.inject.Inject;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding mActivityRegistrationBinding;

    @Inject
    RegistrationViewModel registrationViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Binding View
        mActivityRegistrationBinding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(mActivityRegistrationBinding.getRoot());

        registrationViewModel = new RegistrationViewModel(((MyApplication) getApplication()).getUserManager());
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
