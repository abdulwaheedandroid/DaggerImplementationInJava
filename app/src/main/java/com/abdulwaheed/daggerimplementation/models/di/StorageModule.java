package com.abdulwaheed.daggerimplementation.models.di;

import com.abdulwaheed.daggerimplementation.models.repositories.sharedpreferences.SharedPreferenceStorage;
import com.abdulwaheed.daggerimplementation.models.repositories.sharedpreferences.Storage;

import dagger.Binds;
import dagger.Module;

//Because of @Binds, StorageModule needs to be an abstract class
@Module //Tells dagger this is a dagger module
public abstract class StorageModule {
    @Binds
    abstract Storage bindStorage(SharedPreferenceStorage sharedPreferenceStorage);
}
