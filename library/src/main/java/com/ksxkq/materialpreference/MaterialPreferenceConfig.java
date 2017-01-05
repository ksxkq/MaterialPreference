package com.ksxkq.materialpreference;

import android.content.Context;
import android.view.View;

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

    public StorageModule getStorageModule() {
        if (mStorageModule == null) {
            mStorageModule = new SharedPreferenceStorageModule();
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
}
