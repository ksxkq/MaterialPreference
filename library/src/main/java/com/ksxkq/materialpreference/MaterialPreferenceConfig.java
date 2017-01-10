package com.ksxkq.materialpreference;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import java.util.WeakHashMap;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class MaterialPreferenceConfig implements OnPreferenceCallback {

    private static MaterialPreferenceConfig instance;
    private WeakHashMap<OnPreferenceCallback, Object> onPreferenceCallbackMap;
    private StorageModule mStorageModule;
    private UserInputModule mUserInputModule;
    private Theme mTheme;

    private MaterialPreferenceConfig() {
        onPreferenceCallbackMap = new WeakHashMap<>();
    }

    public static MaterialPreferenceConfig getInstance() {
        synchronized (MaterialPreferenceConfig.class) {
            if (instance == null) {
                instance = new MaterialPreferenceConfig();
            }
        }

        return instance;
    }

    public StorageModule getStorageModule(Context context) {
        if (mStorageModule == null) {
            mStorageModule = new SharedPreferenceStorageModule(context);
        }
        return mStorageModule;
    }

    public void setStorageModule(StorageModule storageModule) {
        this.mStorageModule = storageModule;
    }

    public UserInputModule getUserInputModule(Context context) {
        if (mUserInputModule == null) {
            mUserInputModule = new DefaultUserInputModule(context);
        }
        return mUserInputModule;
    }

    public void setTheme(Theme theme) {
        this.mTheme = theme;
    }

    public Theme getTheme() {
        return mTheme;
    }

    public void setUserInputModule(UserInputModule userInputModule) {
        this.mUserInputModule = userInputModule;
    }

    void registerOnPreferenceCallback(OnPreferenceCallback onPreferenceCallback) {
        onPreferenceCallbackMap.put(onPreferenceCallback, "");
    }

    void unregisterOnPreferenceCallback(OnPreferenceCallback onPreferenceCallback) {
        onPreferenceCallbackMap.remove(onPreferenceCallback);
    }

    @Override
    public void onPreferenceClick(String key, View view) {
        for (OnPreferenceCallback onPreferenceCallback : onPreferenceCallbackMap.keySet()) {
            onPreferenceCallback.onPreferenceClick(key, view);
        }
    }

    @Override
    public void onSecondIconClick(String key, View view) {
        for (OnPreferenceCallback onPreferenceCallback : onPreferenceCallbackMap.keySet()) {
            onPreferenceCallback.onSecondIconClick(key, view);
        }
    }

    @Override
    public void onSingleChoice(String key, String name, String value, View view) {

    }

    @Override
    public void onProgressChanged(String key, SeekBar seekBar, int progress, boolean isUser) {

    }

    @Override
    public void onStartTrackingTouch(String key, SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(String key, SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(String key, CompoundButton compoundButton, boolean isChecked) {
        for (OnPreferenceCallback onPreferenceCallback : onPreferenceCallbackMap.keySet()) {
            onPreferenceCallback.onCheckedChanged(key, compoundButton, isChecked);
        }
    }

    public static class Theme {
        private
        @ColorInt
        int primaryColor;
        private
        @ColorInt
        int accentColor;

        public int getPrimaryColor() {
            return primaryColor;
        }

        public void setPrimaryColor(int primaryColor) {
            this.primaryColor = primaryColor;
        }

        public int getAccentColor() {
            return accentColor;
        }

        public void setAccentColor(int accentColor) {
            this.accentColor = accentColor;
        }
    }

}
