package com.abdulwaheed.daggerimplementation.models.repositories.sharedpreferences;

public interface Storage {
    String getString(String key);
    void setString(String key, String value);
}
