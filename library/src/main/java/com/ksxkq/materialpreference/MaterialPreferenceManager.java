package com.ksxkq.materialpreference;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.ksxkq.materialpreference.utils.ThemeUtils;

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

        MaterialPreferenceConfig.Theme theme = new MaterialPreferenceConfig.Theme();
        theme.setPrimaryColor(ThemeUtils.getThemeAttrColor(mContext, R.attr.colorPrimary));
        theme.setAccentColor(ThemeUtils.getThemeAttrColor(mContext, R.attr.colorAccent));
        MaterialPreferenceConfig.getInstance().setTheme(theme);
    }

    public MaterialPreferenceManager addPreferenceCategory(String key, @StringRes int titleRes) {
        return addPreferenceCategory(key, mContext.getResources().getString(titleRes));
    }

    public MaterialPreferenceManager addPreferenceCategory(String key, String titleStr) {
        PreferenceCategory preferenceCatalog = new PreferenceCategory(key, titleStr);
        mMaterialPreferenceList.add(preferenceCatalog);
        notifyItemInserted();
        return this;
    }

    public MaterialPreferenceManager addPreferenceScreen(String key, @StringRes int titleRes) {
        return addPreferenceScreen(key, getString(titleRes));
    }

    public MaterialPreferenceManager addPreferenceScreen(String key, String titleStr) {
        PreferenceScreen preferenceScreen = new PreferenceScreen(key, titleStr);
        mMaterialPreferenceList.add(preferenceScreen);
        notifyItemInserted();
        return this;
    }

    public MaterialPreferenceManager addPreferenceList(String key, String title, @ArrayRes int names, @ArrayRes int values) {
        PreferenceList preferenceList = new PreferenceList(key, title, names, values);
        mMaterialPreferenceList.add(preferenceList);
        notifyItemInserted();
        return this;
    }

    public MaterialPreferenceManager addPreferenceSingleChoice() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceMultiChoice() {
        return this;
    }

    public MaterialPreferenceManager addPreferenceSeekbar(String key, @StringRes int titleRes, int defaultValue, int max) {
        return addPreferenceSeekbar(key, getString(titleRes), defaultValue, max);
    }

    public MaterialPreferenceManager addPreferenceSeekbar(String key, String title, int defaultValue, int max) {
        int value = MaterialPreferenceConfig.getInstance().getStorageModule(mContext).getInt(key, defaultValue);
        PreferenceSeekbar preferenceSeekbar = new PreferenceSeekbar(key, title, value, max);
        mMaterialPreferenceList.add(preferenceSeekbar);
        notifyItemInserted();
        return this;
    }

    public MaterialPreferenceManager addPreferenceSwitch(String key, @StringRes int titleRes, boolean defaultValue) {
        return addPreferenceSwitch(key, getString(titleRes), defaultValue);
    }

    public MaterialPreferenceManager addPreferenceSwitch(String key, String title, boolean defaultValue) {
        boolean isChecked = MaterialPreferenceConfig.getInstance().getStorageModule(mContext).getBoolean(key, defaultValue);
        PreferenceSwitch preferenceSwitch = new PreferenceSwitch(key, title, isChecked);
        mMaterialPreferenceList.add(preferenceSwitch);
        notifyItemInserted();
        return this;
    }

    public MaterialPreferenceManager addPreferenceCheckbox(String key, @StringRes int titleRes, boolean defaultValue) {
        return addPreferenceCheckbox(key, getString(titleRes), defaultValue);
    }

    public MaterialPreferenceManager addPreferenceCheckbox(String key, String title, boolean defaultValue) {
        boolean isChecked = MaterialPreferenceConfig.getInstance().getStorageModule(mContext).getBoolean(key, defaultValue);
        PreferenceCheckbox preferenceCheckbox = new PreferenceCheckbox(key, title, isChecked);
        mMaterialPreferenceList.add(preferenceCheckbox);
        notifyItemInserted();
        return this;
    }

    public MaterialPreferenceManager addPreferenceEditText() {
        return this;
    }

    public MaterialPreferenceManager addPreference(BasePreference preference) {
        mMaterialPreferenceList.add(preference);
        notifyItemInserted();
        return this;
    }

    public MaterialPreferenceManager addPreferences(List<BasePreference> materialPreferenceList) {
        int positionStart = mMaterialPreferenceList.size();
        mMaterialPreferenceList.addAll(materialPreferenceList);
        if (mAdapter != null) {
            mAdapter.notifyItemRangeChanged(positionStart, materialPreferenceList.size());
        }
        return this;
    }

    public void updatePreference(BasePreference updatePreference) {
        if (mAdapter != null) {
            for (int i = 0; i < mMaterialPreferenceList.size(); i++) {
                BasePreference preference = mMaterialPreferenceList.get(i);
                if (TextUtils.equals(updatePreference.getKey(), preference.getKey())) {
                    mAdapter.notifyItemChanged(i);
                    break;
                }
            }
        }
    }

    public void updatePreferences(List<BasePreference> materialPreferenceList) {
        if (mAdapter != null) {
            mAdapter.setItems(materialPreferenceList);
        } else {
            mMaterialPreferenceList.clear();
            mMaterialPreferenceList.addAll(materialPreferenceList);
        }
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
        if (mAdapter != null) {
            mAdapter.notifyItemInserted(position);
        }
        return this;
    }

    @Nullable
    public BasePreference getPreference(String key) {
        for (BasePreference preference : mMaterialPreferenceList) {
            if (TextUtils.equals(preference.getKey(), key)) {
                return preference;
            }
        }
        return null;
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

    /**
     * Item 增加后通知更新
     */
    private void notifyItemInserted() {
        if (mAdapter != null) {
            int position = mMaterialPreferenceList.size() - 1; // 因为已经添加过了，所以 -1
            mAdapter.notifyItemInserted(position);
        }
    }

    private String getString(@StringRes int res) {
        return mContext.getResources().getString(res);
    }

}
