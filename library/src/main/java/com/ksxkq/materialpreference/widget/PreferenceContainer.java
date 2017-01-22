package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.OnPreferenceCallback;
import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xukq on 1/17/16.
 */
public class PreferenceContainer extends ScrollView {

    private Map<String, BasePreference> mPreferenceMap = new HashMap<>();
    private LinearLayout mContainer;
    private String mContainerKey;

    public PreferenceContainer(Context context) {
        super(context);
        init(context);
    }

    public PreferenceContainer(Context context, String containerKey) {
        this(context);
        mContainerKey = containerKey;
    }

    public PreferenceContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PreferenceContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContainer = (LinearLayout) inflate(context, R.layout.preference_container_ll, null);
        addView(mContainer);
        setBackgroundResource(R.color.bg_activity);
    }

    public PreferenceContainer addPreference(BasePreference preference) {
        mContainer.addView(preference);
        mPreferenceMap.put(preference.key, preference);
        return this;
    }

    public PreferenceContainer addPreferences(List<BasePreference> preferences) {
        for (int i = 0; i < preferences.size(); i++) {
            BasePreference preference = preferences.get(i);
            // 避免重复添加
            if (!mPreferenceMap.containsKey(preference.key)) {
                mContainer.addView(preference);
                mPreferenceMap.put(preference.key, preference);
            }
        }
        return this;
    }

    public void removePreference(BasePreference preference) {
        if (preference != null) {
            mContainer.removeView(preference);
            mPreferenceMap.remove(preference.key);
        }
    }

    public void removePreferences(List<BasePreference> preferences) {
        for (int i = 0; i < preferences.size(); i++) {
            BasePreference preference = preferences.get(i);
            removePreference(preference);
        }
    }

    public void removePreference(String key) {
        BasePreference preference = mPreferenceMap.get(key);
        removePreference(preference);
    }

    public PreferenceContainer addCategoryPreference(String key, @StringRes int titleRes) {
        addCategoryPreference(key, getStr(titleRes));
        return this;
    }

    public PreferenceContainer addCategoryPreference(String key, String title) {
        CategoryPreference categoryPreference = new CategoryPreference(getContext(), key, title);
        mContainer.addView(categoryPreference);
        return this;
    }

    public PreferenceContainer addSwitchPreference(String key, @StringRes int titleRes, boolean defaultValue) {
        addSwitchPreference(key, getStr(titleRes), defaultValue);
        return this;
    }

    public PreferenceContainer addSwitchPreference(String key, String title, boolean defaultValue) {
        SwitchPreference switchPreference = new SwitchPreference(getContext(), key, title, defaultValue);
        mContainer.addView(switchPreference);
        mPreferenceMap.put(key, switchPreference);
        return this;
    }

    public PreferenceContainer addCheckBoxPreference(String key, @StringRes int titleRes, boolean defaultValue) {
        CheckBoxPreference checkBoxPreference = new CheckBoxPreference(getContext(), key, titleRes, defaultValue);
        mContainer.addView(checkBoxPreference);
        mPreferenceMap.put(key, checkBoxPreference);
        return this;
    }

    public PreferenceContainer addSeekbarPreference(String key, @StringRes int titleRes, int defaultValue, int max) {
        addSeekbarPreference(key, getStr(titleRes), defaultValue, max);
        return this;
    }

    public PreferenceContainer addSeekbarPreference(String key, String title, int defaultValue, int max) {
        SeekbarPreference seekbarPreference = new SeekbarPreference(getContext(), key, title, defaultValue, max);
        mContainer.addView(seekbarPreference);
        mPreferenceMap.put(key, seekbarPreference);
        return this;
    }

    public PreferenceContainer addListPreference(String key, @StringRes int titleRes, @ArrayRes int itemNames, @ArrayRes int itemValues) {
        ListPreference listPreference = new ListPreference(getContext(), key, titleRes, itemNames, itemValues);
        mContainer.addView(listPreference);
        mPreferenceMap.put(key, listPreference);
        return this;
    }

    public PreferenceContainer addScreenPreference(String key, @StringRes int titleRes) {
        addScreenPreference(key, getContext().getResources().getString(titleRes));
        return this;
    }

    public PreferenceContainer addInstructionScreenPreference(String key, @StringRes int titleRes, @StringRes int instructionRes) {
        addScreenPreference(key, titleRes);
        ScreenPreference screenPreference = getScreenPreference(key);
//        screenPreference.setInstruction(instructionRes);
        return this;
    }

    public PreferenceContainer addScreenPreference(String key, String title) {
        ScreenPreference screenPreference = new ScreenPreference(getContext(), key, title);
        mContainer.addView(screenPreference);
        mPreferenceMap.put(key, screenPreference);
        return this;
    }

    public PreferenceContainer addScreenPreference(String key, @StringRes int titleRes, @DrawableRes int rightIconRes) {
        addScreenPreference(key, titleRes);
        getScreenPreference(key).setRightIcon(rightIconRes);
        return this;
    }

    public PreferenceContainer addLine() {
        View line = LayoutInflater.from(getContext()).inflate(R.layout.line, mContainer, false);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) line.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = Utils.dpToPixels(getContext(), 1) / 2;
        int marginLeftRight = getResources().getDimensionPixelSize(R.dimen.material_preference_padding_left_right);
        params.leftMargin = marginLeftRight;
        params.rightMargin = marginLeftRight;
        mContainer.addView(line, params);
        return this;
    }

    public ScreenPreference getScreenPreference(String key) {
        return (ScreenPreference) mPreferenceMap.get(key);
    }

    public CheckBoxPreference getCheckBoxPreference(String key) {
        return (CheckBoxPreference) mPreferenceMap.get(key);
    }

    public SeekbarPreference getSeekbarPreference(String key) {
        return (SeekbarPreference) mPreferenceMap.get(key);
    }

    public Map<String, BasePreference> getPreferenceMap() {
        return mPreferenceMap;
    }

    public LinearLayout getContainer() {
        return mContainer;
    }

    public String getContainerKey() {
        return mContainerKey;
    }

    private String getStr(@StringRes int id) {
        return getResources().getString(id);
    }

    public void registerCallback(OnPreferenceCallback onPreferenceCallback) {
        MaterialPreferenceConfig.getInstance().registerOnPreferenceCallback(onPreferenceCallback);
    }

    public void unregisterCallback(OnPreferenceCallback onPreferenceCallback) {
        MaterialPreferenceConfig.getInstance().unregisterOnPreferenceCallback(onPreferenceCallback);
    }
}
