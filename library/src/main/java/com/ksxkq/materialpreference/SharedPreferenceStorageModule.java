package com.ksxkq.materialpreference;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class SharedPreferenceStorageModule implements StorageModule {

    @Override
    public void putBoolean(String key, boolean value) {

    }

    @Override
    public void putString(String key, String value) {

    }

    @Override
    public void putInt(String key, int value) {

    }

    @Override
    public boolean getBoolean(String key, boolean defaultVal) {
        return false;
    }

    @Override
    public String getString(String key, String defaultVal) {
        return null;
    }

    @Override
    public int getInt(String key, int defaultVal) {
        return 0;
    }

}
