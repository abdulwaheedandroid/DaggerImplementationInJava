package com.abdulwaheed.daggerimplementation.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.abdulwaheed.daggerimplementation.R;
import com.abdulwaheed.daggerimplementation.databinding.FragmentEnterDetailsBinding;
import com.abdulwaheed.daggerimplementation.view_models.EnterDetailsViewModel;
import com.abdulwaheed.daggerimplementation.view_models.RegistrationViewModel;
import com.abdulwaheed.daggerimplementation.views.activities.RegistrationActivity;

/**
 * RegistrationViewModel is used to set the username and password information (attached to
 * Activity's lifecycle and shared between different fragments)
 * EnterDetailsViewModel is used to validate the user input (attached to this
 * Fragment's lifecycle)
 *
 * They could get combined but for the sake of the codelab, we're separating them so we have
 * different ViewModels with different lifecycles.
 */

public class EnterDetailsFragment extends Fragment {

    private FragmentEnterDetailsBinding fragmentEnterDetailsBinding;
    private RegistrationViewModel registrationViewModel;
    private EnterDetailsViewModel enterDetailsViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_details, container, false);

        return view;
    }

    private void setListeners() {
        fragmentEnterDetailsBinding.next.setOnClickListener(v -> {
            final String userName = fragmentEnterDetailsBinding.username.getText().toString();
            final String password = fragmentEnterDetailsBinding.password.getText().toString();

            enterDetailsViewModel.validateInput(userName, password);
        });
    }

    private void initInstanceVariables() {
        enterDetailsViewModel = new EnterDetailsViewModel();
        registrationViewModel = ((RegistrationActivity) getActivity()). getRegistrationViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentEnterDetailsBinding = FragmentEnterDetailsBinding.bind(view);

        initInstanceVariables();
        setListeners();

        enterDetailsViewModel.getEnterDetailsState().observe(this, o -> {
            if (o instanceof EnterDetailsSuccess) {
                final String userName = fragmentEnterDetailsBinding.username.getText().toString();
                final String password = fragmentEnterDetailsBinding.password.getText().toString();

                registrationViewModel.updateUserData(userName, password);

                ((RegistrationActivity) getActivity()).onDetailsEntered();
            } else if (o instanceof EnterDetailsError) {
                fragmentEnterDetailsBinding.error.setText(((EnterDetailsError)o).error );
                fragmentEnterDetailsBinding.error.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public void onDestroyView() {
        fragmentEnterDetailsBinding = null;
        super.onDestroyView();
    }
    
    public class EnterDetailsViewState {
        
    }
    
    public class EnterDetailsSuccess extends EnterDetailsViewState {
        
    }
    
    public class EnterDetailsError extends EnterDetailsViewState {
        private String error;

        public EnterDetailsError(String error) {
            this.error = error;
        }
    }
}
