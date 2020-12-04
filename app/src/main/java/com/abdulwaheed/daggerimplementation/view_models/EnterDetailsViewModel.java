package com.abdulwaheed.daggerimplementation.view_models;

import androidx.lifecycle.MutableLiveData;

import com.abdulwaheed.daggerimplementation.views.fragments.EnterDetailsFragment;

public class EnterDetailsViewModel {

    public static final byte MAX_LENGTH = 5;

    private MutableLiveData<EnterDetailsFragment.EnterDetailsViewState> enterDetailsMutableLiveData = new MutableLiveData();
    //private LiveData<EnterDetailsFragment.EnterDetailsViewState> enterDetailsViewStateLiveData;

    public MutableLiveData getEnterDetailsState() {
        return enterDetailsMutableLiveData;
    }

    public void validateInput(final String userName, final String password) {
        if (userName.length() < MAX_LENGTH) {
            enterDetailsMutableLiveData.postValue(new EnterDetailsFragment().new EnterDetailsError("User name has to be longer than 4 characters"));
        } else if (password.length() < MAX_LENGTH) {
            enterDetailsMutableLiveData.postValue(new EnterDetailsFragment().new EnterDetailsError("Password has to be longer than 4 characters"));
        } else {
            enterDetailsMutableLiveData.postValue(new EnterDetailsFragment().new EnterDetailsSuccess());
        }
    }
}
