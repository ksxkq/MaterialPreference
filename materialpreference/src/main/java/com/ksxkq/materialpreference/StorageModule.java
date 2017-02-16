package com.ksxkq.materialpreference;

/**
 * Created by yarolegovich on 15.05.2016.
 */
public interface StorageModule {

    void putBoolean(String key, boolean value);

    void putString(String key, String value);

    void putInt(String key, int value);

    boolean getBoolean(String key, boolean defaultVal);

    String getString(String key, String defaultVal);

    int getInt(String key, int defaultVal);

}
