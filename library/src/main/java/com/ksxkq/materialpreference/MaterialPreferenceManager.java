package com.ksxkq.materialpreference;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ksxkq.materialpreference.preferences.AbsMaterialPreference;
import com.ksxkq.materialpreference.preferences.PreferenceCatalogProvider;
import com.ksxkq.materialpreference.preferences.PreferenceCategory;

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

    public MaterialPreferenceManager addPreferenceScreen() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceList() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceSingleChoice() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceMultiChoice() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceSeekbar() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceSwitch() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceEditText() {
        return this;
    }

    /**
     * 在指定 Preference 后面添加 Preference
     *
     * @param key        指定 Preference 的 key
     * @param preference 添加的 Preference
     * @return MaterialPreferenceManager
     */
    public MaterialPreferenceManager addPreferenceBehind(String key, AbsMaterialPreference preference) {
        return this;
    }

    public void removePreference(String key) {

    }

    public void apply() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MultiTypeAdapter(mMaterialPreferenceList);
        mAdapter.register(PreferenceCategory.class, new PreferenceCatalogProvider());
        mRecyclerView.setAdapter(mAdapter);
    }

    public MaterialPreferenceManager registerCallback(OnPreferenceCallback onPreferenceCallback) {
        MaterialPreferenceConfig.getInstance().registerOnPreferenceCallback(onPreferenceCallback);
        return this;
    }

    public void unregisterCallback(OnPreferenceCallback onPreferenceCallback) {
        MaterialPreferenceConfig.getInstance().unregisterOnPreferenceCallback(onPreferenceCallback);
    }

}
