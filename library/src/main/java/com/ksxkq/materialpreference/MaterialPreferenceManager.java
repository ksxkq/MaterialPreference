package com.ksxkq.materialpreference;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ksxkq.materialpreference.preferences.PreferenceCatalogProvider;
import com.ksxkq.materialpreference.preferences.PreferenceCategory;
import com.ksxkq.materialpreference.preferences.PreferenceList;
import com.ksxkq.materialpreference.preferences.PreferenceListProvider;
import com.ksxkq.materialpreference.preferences.PreferenceScreen;
import com.ksxkq.materialpreference.preferences.PreferenceScreenProvider;
import com.ksxkq.materialpreference.preferences.PreferenceSeekbar;
import com.ksxkq.materialpreference.preferences.PreferenceSeekbarProvider;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class MaterialPreferenceManager {

    private List<Object> mMaterialPreferenceList;
    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
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

    public MaterialPreferenceManager addPreferenceSeekbar(String key, String title, int defaultValue, int min, int max) {
        int value = MaterialPreferenceConfig.getInstance().getStorageModule().getInt(key, defaultValue);
        PreferenceSeekbar preferenceSeekbar = new PreferenceSeekbar(key, title, value, min, max);
        mMaterialPreferenceList.add(preferenceSeekbar);
        return this;
    }

    public MaterialPreferenceManager addPreferenceSwitch() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceEditText() {
        return this;
    }

    /**
     * 在指定 BasePreference 后面添加 BasePreference
     *
     * @param key        指定 BasePreference 的 key
     * @param preference 添加的 BasePreference
     * @return MaterialPreferenceManager
     */
    public MaterialPreferenceManager addPreferenceBehind(String key, Object preference) {
        return this;
    }

    public void removePreference(String key) {

    }

    public void apply() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapter = new MultiTypeAdapter(mMaterialPreferenceList);

        mAdapter.register(PreferenceCategory.class, new PreferenceCatalogProvider());
        mAdapter.register(PreferenceScreen.class, new PreferenceScreenProvider());
        mAdapter.register(PreferenceList.class, new PreferenceListProvider());
        mAdapter.register(PreferenceSeekbar.class, new PreferenceSeekbarProvider());

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
