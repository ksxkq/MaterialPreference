package com.ksxkq.materialpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class SharedPreferenceStorageModule implements StorageModule {

    private static final String DEFAULT_SP_NAME = "material_preference_sp";
    private final SharedPreferences mSharedPref;
    private SharedPreferences.Editor mEditor;

    public SharedPreferenceStorageModule(Context context) {
        mSharedPref = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPref.edit();
    }

    @Override
    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    @Override
    public void putString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    @Override
    public void putInt(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    @Override
    public boolean getBoolean(String key, boolean defaultVal) {
        return mSharedPref.getBoolean(key, defaultVal);
    }

    @Override
    public String getString(String key, String defaultVal) {
        return mSharedPref.getString(key, defaultVal);
    }

    @Override
    public int getInt(String key, int defaultVal) {
        return mSharedPref.getInt(key, defaultVal);
    }

}
