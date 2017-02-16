package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.ViewGroup;

import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.utils.Utils;

/**
 * Created by xukq on 1/16/16.
 */
public class CategoryPreference extends BasePreference {


    public CategoryPreference(Context context, String key, String title) {
        super(context, key, title);
    }

    public CategoryPreference(Context context, String key, @StringRes int titleRes) {
        this(context, key, context.getResources().getString(titleRes));
    }

    @Override
    protected void logic() {
        setClickable(false);
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_catalog;
    }

    @Override
    protected void onConfigureSelf() {
        super.onConfigureSelf();
        int padding = Utils.dpToPixels(getContext(), 16);
        setPadding(padding, padding / 2, padding, padding / 4);
        setClickable(false);
        setBackgroundResource(R.color.material_preference_catalog_background);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }
}
