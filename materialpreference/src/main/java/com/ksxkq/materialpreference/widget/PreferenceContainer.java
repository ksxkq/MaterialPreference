package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.OnPreferenceCallback;
import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xukq on 1/17/16.
 */
public class PreferenceContainer extends NestedScrollView {

    public static final String TAG = PreferenceContainer.class.getSimpleName();
    private static final int NOT_ADD = -1;

    private Map<String, BasePreference> mPreferenceMap = new HashMap<>();
    private List<String> mPositionList = new ArrayList<>();
    private LinearLayout mContainer;
    private String mContainerKey;

    public PreferenceContainer(Context context) {
        super(context);
        init(context, null);
    }

    public PreferenceContainer(Context context, String containerKey) {
        this(context);
        mContainerKey = containerKey;
    }

    public PreferenceContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PreferenceContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        boolean animateLayoutChanges = true;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.mp);
            animateLayoutChanges = a.getBoolean(R.styleable.mp_animateLayoutChanges, true);
            a.recycle();
        }

        int preference_container_ll_res = animateLayoutChanges ? R.layout.preference_container_ll : R.layout.preference_container_ll_no_anim;
        mContainer = (LinearLayout) inflate(context, preference_container_ll_res, null);
        addView(mContainer);
        setBackgroundResource(R.color.bg_activity);
    }

    public PreferenceContainer addPreference(BasePreference preference) {
        if (mPreferenceMap.containsKey(preference.key)) {
            Log.e(TAG, preference.key + " is already added");
        }

        // 避免重复添加
        if (!mPreferenceMap.containsKey(preference.key)) {
            mContainer.addView(preference);
            mPreferenceMap.put(preference.key, preference);
            mPositionList.add(preference.key);
        } else {

        }
        return this;
    }

    public PreferenceContainer addPreferenceBehindKey(BasePreference preference, String key) {
        int pointPosition = getPositionByKey(key);
        int index = pointPosition + 1;
        if (NOT_ADD != pointPosition && index <= mContainer.getChildCount()) {
            mContainer.addView(preference, index);
        }
        return this;
    }

    public PreferenceContainer addPreferences(List<BasePreference> preferences) {
        for (int i = 0; i < preferences.size(); i++) {
            BasePreference preference = preferences.get(i);
            addPreference(preference);
        }
        return this;
    }

    public void removePreference(BasePreference preference) {
        if (preference != null) {
            mContainer.removeView(preference);
            mPreferenceMap.remove(preference.key);
            mPositionList.remove(preference.key);
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
        addPreference(categoryPreference);
        return this;
    }

    public PreferenceContainer addSwitchPreference(String key, @StringRes int titleRes, boolean defaultValue) {
        addSwitchPreference(key, getStr(titleRes), defaultValue);
        return this;
    }

    public PreferenceContainer addSwitchPreference(String key, String title, boolean defaultValue) {
        SwitchPreference switchPreference = new SwitchPreference(getContext(), key, title, defaultValue);
        addPreference(switchPreference);
        return this;
    }

    public PreferenceContainer addCheckBoxPreference(String key, @StringRes int titleRes, boolean defaultValue) {
        addCheckBoxPreference(key, getStr(titleRes), defaultValue);
        return this;
    }

    public PreferenceContainer addCheckBoxPreference(String key, String title, boolean defaultValue) {
        CheckBoxPreference checkBoxPreference = new CheckBoxPreference(getContext(), key, title, defaultValue);
        addPreference(checkBoxPreference);
        return this;
    }

    public PreferenceContainer addSeekbarPreference(String key, @StringRes int titleRes, int defaultValue, int max) {
        addSeekbarPreference(key, getStr(titleRes), defaultValue, max);
        return this;
    }

    public PreferenceContainer addSeekbarPreference(String key, String title, int defaultValue, int max) {
        SeekbarPreference seekbarPreference = new SeekbarPreference(getContext(), key, title, defaultValue, max);
        addPreference(seekbarPreference);
        return this;
    }

    public PreferenceContainer addListPreference(String key, @StringRes int titleRes, @ArrayRes int itemNames, @ArrayRes int itemValues) {
        addListPreference(key, getStr(titleRes), itemNames, itemValues);
        return this;
    }

    public PreferenceContainer addListPreference(String key, String title, @ArrayRes int itemNames, @ArrayRes int itemValues) {
        ListPreference listPreference = new ListPreference(getContext(), key, title, itemNames, itemValues);
        addPreference(listPreference);
        return this;
    }

    public PreferenceContainer addScreenPreference(String key, @StringRes int titleRes) {
        addScreenPreference(key, getContext().getResources().getString(titleRes));
        return this;
    }

    public PreferenceContainer addScreenPreference(String key, String title) {
        ScreenPreference screenPreference = new ScreenPreference(getContext(), key, title);
        addPreference(screenPreference);
        return this;
    }

    public PreferenceContainer addScreenPreference(String key, @StringRes int titleRes, @DrawableRes int rightIconRes) {
        addScreenPreference(key, titleRes);
        ScreenPreference screenPreference = getPreference(key);
        screenPreference.setRightIcon(rightIconRes);
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

    public <T> T getPreference(String key) {
        return (T) mPreferenceMap.get(key);
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

    public boolean contains(String key) {
        return mPreferenceMap.containsKey(key);
    }

    /**
     * 获取对应 key 所在位置
     * @param key
     * @return 如果找不到，返回 NOT_ADD；找到返回对应位置
     */
    private int getPositionByKey(String key) {
        for (int i = 0; i < mPositionList.size(); i++) {
            if (TextUtils.equals(key, mPositionList.get(i))) {
                return i;
            }
        }
        return NOT_ADD;
    }
}
