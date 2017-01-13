package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.OnPreferenceCallback;
import com.ksxkq.materialpreference.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xukq on 1/17/16.
 */
public class PreferenceContainer extends LinearLayout {

    private Map<String, BasePreference> mPreferenceMap = new HashMap<>();
    private String mContainerKey;

    public PreferenceContainer(Context context) {
        super(context);
        setOrientation(VERTICAL);
        setBackgroundResource(R.color.material_preference_catalog_background);
    }

    public PreferenceContainer(Context context, String containerKey) {
        this(context);
        mContainerKey = containerKey;
    }

    public PreferenceContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public PreferenceContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    public PreferenceContainer addPreference(BasePreference preference) {
        return this;
    }

    public PreferenceContainer addCatalogPreference(String key, @StringRes int titleRes) {
        addCatalogPreference(key, getStr(titleRes));
        return this;
    }

    public PreferenceContainer addCatalogPreference(String key, String title) {
        CatalogPreference catalogPreference = new CatalogPreference(getContext(), key);
        catalogPreference.setTitle(title);
        addView(catalogPreference);
        return this;
    }

    public PreferenceContainer addSwitchPreference(String key, @StringRes int titleRes, boolean defaultValue) {
        addSwitchPreference(key, getStr(titleRes), defaultValue);
        return this;
    }

    public PreferenceContainer addSwitchPreference(String key, String title, boolean defaultValue) {
        SwitchPreference switchPreference = new SwitchPreference(getContext(), key, defaultValue);
        switchPreference.setTitle(title);
        addView(switchPreference);
        mPreferenceMap.put(key, switchPreference);
        return this;
    }

    public PreferenceContainer addCheckBoxPreference(String key, @StringRes int titleRes, boolean defaultValue) {
        CheckBoxPreference checkBoxPreference = new CheckBoxPreference(getContext(), key, defaultValue);
        checkBoxPreference.setTitle(titleRes);
        addView(checkBoxPreference);
        mPreferenceMap.put(key, checkBoxPreference);
        return this;
    }

    public PreferenceContainer addSeekbarPreference(String key, @StringRes int titleRes) {
        SeekbarPreference seekbarPreference = new SeekbarPreference(getContext(), key);
        seekbarPreference.setTitle(titleRes);
        addView(seekbarPreference);
        mPreferenceMap.put(key, seekbarPreference);
        return this;
    }

    public PreferenceContainer addSeekbarPreference(String key, @StringRes int titleRes, int max) {
        SeekbarPreference seekbarPreference = new SeekbarPreference(getContext(), key, max);
        seekbarPreference.setTitle(titleRes);
        addView(seekbarPreference);
        mPreferenceMap.put(key, seekbarPreference);
        return this;
    }

    public PreferenceContainer addSeekbarPreference(String key, @StringRes int titleRes, int defaultValue, int max) {
        addSeekbarPreference(key, getStr(titleRes), defaultValue, max);
        return this;
    }

    public PreferenceContainer addSeekbarPreference(String key, String title, int defaultValue, int max) {
        SeekbarPreference seekbarPreference = new SeekbarPreference(getContext(), key, defaultValue, max);
        seekbarPreference.setTitle(title);
        addView(seekbarPreference);
        mPreferenceMap.put(key, seekbarPreference);
        return this;
    }

    public PreferenceContainer addListPreference(String key, @StringRes int titleRes, @ArrayRes int itemNames, @ArrayRes int itemValues) {
        ListPreference listPreference = new ListPreference(getContext(), key, itemNames, itemValues);
        listPreference.setTitle(titleRes);
        addView(listPreference);
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
        ScreenPreference screenPreference = new ScreenPreference(getContext(), key);
        screenPreference.setTitle(title);
        addView(screenPreference);
        mPreferenceMap.put(key, screenPreference);
        return this;
    }

    public PreferenceContainer addScreenPreference(String key, @StringRes int titleRes, @DrawableRes int rightIconRes) {
        addScreenPreference(key, titleRes);
        getScreenPreference(key).setRightIcon(rightIconRes);
        return this;
    }

//    public PreferenceContainer addLine() {
//        View line = LayoutInflater.from(getContext()).inflate(R.layout.line, this, false);
//        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
//        params.leftMargin = Utils.dip2px(getContext(), 8);
//        params.rightMargin = Utils.dip2px(getContext(), 8);
//        addView(line, params);
//        return this;
//    }

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

    private boolean isSide(String key) {
        return key.toLowerCase().contains("side");
    }

    private boolean isTop(String key) {
        return key.toLowerCase().contains("top");
    }

    private boolean isBottom(String key) {
        return key.toLowerCase().contains("bottom");
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
