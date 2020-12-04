package com.abdulwaheed.daggerimplementation.models.repositories.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceStorage implements Storage {

    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPreferenceStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    @Override
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }

    @Override
    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }
}
