package com.ksxkq.materialpreference;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.ksxkq.materialpreference.preferences.BasePreference;
import com.ksxkq.materialpreference.preferences.PreferenceCatalogProvider;
import com.ksxkq.materialpreference.preferences.PreferenceCategory;
import com.ksxkq.materialpreference.preferences.PreferenceCheckbox;
import com.ksxkq.materialpreference.preferences.PreferenceCheckboxProvider;
import com.ksxkq.materialpreference.preferences.PreferenceList;
import com.ksxkq.materialpreference.preferences.PreferenceListProvider;
import com.ksxkq.materialpreference.preferences.PreferenceScreen;
import com.ksxkq.materialpreference.preferences.PreferenceScreenProvider;
import com.ksxkq.materialpreference.preferences.PreferenceSeekbar;
import com.ksxkq.materialpreference.preferences.PreferenceSeekbarProvider;
import com.ksxkq.materialpreference.preferences.PreferenceSwitch;
import com.ksxkq.materialpreference.preferences.PreferenceSwitchProvider;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.ItemViewProvider;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class MaterialPreferenceManager {

    private List<BasePreference> mMaterialPreferenceList;
    private RecyclerView mRecyclerView;
    private DiffMultiTypeAdapter mAdapter;
    private Context mContext;

    public MaterialPreferenceManager(RecyclerView recyclerView) {
        mMaterialPreferenceList = new ArrayList<>();
        mRecyclerView = recyclerView;
        mContext = mRecyclerView.getContext();
    }

    public MaterialPreferenceManager addPreferenceCatalog(String key, @StringRes int titleRes) {
        return addPreferenceCatalog(key, mContext.getResources().getString(titleRes));
    }

    public MaterialPreferenceManager addPreferenceCatalog(String key, String titleStr) {
        PreferenceCategory preferenceCatalog = new PreferenceCategory(key, titleStr);
        mMaterialPreferenceList.add(preferenceCatalog);
        return this;
    }

    public MaterialPreferenceManager addPreferenceScreen(String key, @StringRes int titleRes) {
        PreferenceScreen preferenceScreen = new PreferenceScreen(key, getString(titleRes));
        mMaterialPreferenceList.add(preferenceScreen);
        return this;
    }

    public MaterialPreferenceManager addPreferenceList(String key, String title, @ArrayRes int names, @ArrayRes int values) {
        PreferenceList preferenceList = new PreferenceList(key, title, names, values);
        mMaterialPreferenceList.add(preferenceList);
        return this;
    }

    public MaterialPreferenceManager addPreferenceSingleChoice() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceMultiChoice() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceSeekbar(String key, String title, int defaultValue, int max) {
        int value = MaterialPreferenceConfig.getInstance().getStorageModule(mContext).getInt(key, defaultValue);
        PreferenceSeekbar preferenceSeekbar = new PreferenceSeekbar(key, title, value, max);
        mMaterialPreferenceList.add(preferenceSeekbar);
        return this;
    }

    public MaterialPreferenceManager addPreferenceSwitch(String key, String title, boolean defaultValue) {
        boolean isChecked = MaterialPreferenceConfig.getInstance().getStorageModule(mContext).getBoolean(key, defaultValue);
        PreferenceSwitch preferenceSwitch = new PreferenceSwitch(key, title, isChecked);
        mMaterialPreferenceList.add(preferenceSwitch);
        return this;
    }

    public MaterialPreferenceManager addPreferenceCheckbox(String key, String title, boolean defaultValue) {
        boolean isChecked = MaterialPreferenceConfig.getInstance().getStorageModule(mContext).getBoolean(key, defaultValue);
        PreferenceCheckbox preferenceCheckbox = new PreferenceCheckbox(key, title, isChecked);
        mMaterialPreferenceList.add(preferenceCheckbox);
        return this;
    }

    public MaterialPreferenceManager addPreferenceEditText() {
        return this;
    }

    public MaterialPreferenceManager addPreferences(List<BasePreference> materialPreferenceList) {
        if (mAdapter != null) {
            mAdapter.setItems(materialPreferenceList);
        } else {
            mMaterialPreferenceList.addAll(materialPreferenceList);
        }
        return this;
    }

    /**
     * 在指定 BasePreference 后面添加 BasePreference
     *
     * @param key           指定 BasePreference 的 key
     * @param newPreference 待添加的 BasePreference
     * @return MaterialPreferenceManager
     */
    public MaterialPreferenceManager appendPreferenceBehindKey(String key, BasePreference newPreference) {
        int position = mMaterialPreferenceList.size();
        for (int i = 0; i < mMaterialPreferenceList.size() - 1; i++) {
            BasePreference preference = mMaterialPreferenceList.get(i);
            if (TextUtils.equals(preference.getKey(), key)) {
                position = i;
                break;
            }
        }
        mMaterialPreferenceList.add(position, newPreference);
        mAdapter.notifyItemInserted(position);
        return this;
    }

    public void removePreference(String key) {
        int position = -1;
        for (int i = 0; i < mMaterialPreferenceList.size() - 1; i++) {
            BasePreference preference = mMaterialPreferenceList.get(i);
            if (TextUtils.equals(preference.getKey(), key)) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            mMaterialPreferenceList.remove(position);
            mAdapter.notifyItemRemoved(position);
        }
    }

    public MaterialPreferenceManager register(@NonNull Class<?> clazz, @NonNull ItemViewProvider provider) {
        mAdapter.register(clazz, provider);
        return this;
    }

    public void apply() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapter = new DiffMultiTypeAdapter(mMaterialPreferenceList);

        mAdapter.register(PreferenceCategory.class, new PreferenceCatalogProvider());
        mAdapter.register(PreferenceScreen.class, new PreferenceScreenProvider());
        mAdapter.register(PreferenceList.class, new PreferenceListProvider());
        mAdapter.register(PreferenceSeekbar.class, new PreferenceSeekbarProvider());
        mAdapter.register(PreferenceSwitch.class, new PreferenceSwitchProvider());
        mAdapter.register(PreferenceCheckbox.class, new PreferenceCheckboxProvider());

        mRecyclerView.setAdapter(mAdapter);
    }

    public MaterialPreferenceManager registerCallback(OnPreferenceCallback onPreferenceCallback) {
        MaterialPreferenceConfig.getInstance().registerOnPreferenceCallback(onPreferenceCallback);
        return this;
    }

    public void unregisterCallback(OnPreferenceCallback onPreferenceCallback) {
        MaterialPreferenceConfig.getInstance().unregisterOnPreferenceCallback(onPreferenceCallback);
    }

    private String getString(@StringRes int res) {
        return mContext.getResources().getString(res);
    }

}
