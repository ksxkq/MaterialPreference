package com.ksxkq.materialpreference;

import android.content.Context;
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
    private WeakHashMap<OnPreferenceCallback, Object> onPreferenceCallbackList;
    private StorageModule mStorageModule;
    private UserInputModule mUserInputModule;

    private MaterialPreferenceConfig() {
        onPreferenceCallbackList = new WeakHashMap<>();
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

    public void setUserInputModule(UserInputModule userInputModule) {
        this.mUserInputModule = userInputModule;
    }

    void registerOnPreferenceCallback(OnPreferenceCallback onPreferenceCallback) {
        onPreferenceCallbackList.put(onPreferenceCallback, "");
    }

    void unregisterOnPreferenceCallback(OnPreferenceCallback onPreferenceCallback) {
        onPreferenceCallbackList.remove(onPreferenceCallback);
    }

    @Override
    public void onClick(String key, View view) {
        for (OnPreferenceCallback onPreferenceCallback : onPreferenceCallbackList.keySet()) {
            onPreferenceCallback.onClick(key, view);
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

    }
}
