package com.abdulwaheed.daggerimplementation.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abdulwaheed.daggerimplementation.R;
import com.abdulwaheed.daggerimplementation.databinding.FragmentTermsAndConditionsBinding;
import com.abdulwaheed.daggerimplementation.view_models.RegistrationViewModel;
import com.abdulwaheed.daggerimplementation.views.activities.RegistrationActivity;

public class TermsAndConditionsFragment extends Fragment {

    private FragmentTermsAndConditionsBinding fragmentTermsAndConditionsBinding;
    private RegistrationViewModel registrationViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentTermsAndConditionsBinding = FragmentTermsAndConditionsBinding.bind(view);
        registrationViewModel = ((RegistrationActivity) getActivity()).getRegistrationViewModel();

        fragmentTermsAndConditionsBinding.next.setOnClickListener(v -> {
            registrationViewModel.acceptTermAndConditions();
            ((RegistrationActivity) getActivity()).onTermsAndConditionsAccepted();
        });
    }
}
