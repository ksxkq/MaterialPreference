package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.StringRes;

import com.ksxkq.materialpreference.R;

/**
 * Created by xukq on 2/27/16.
 */
public class CheckBoxPreference extends CompoundButtonPreference {

    public CheckBoxPreference(Context context, String key, String title, boolean defaultValue) {
        super(context, key, title, defaultValue);
    }

    public CheckBoxPreference(Context context, String key, @StringRes int titleRes, boolean defaultValue) {
        super(context, key, titleRes, defaultValue);
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_checkbox;
    }

}
