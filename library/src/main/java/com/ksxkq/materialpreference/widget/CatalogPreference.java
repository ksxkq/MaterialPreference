package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.StringRes;

import com.ksxkq.materialpreference.R;

/**
 * Created by xukq on 1/16/16.
 */
public class CatalogPreference extends BasePreference {


    public CatalogPreference(Context context, String key, String title) {
        super(context, key, title);
    }

    public CatalogPreference(Context context, String key, @StringRes int titleRes) {
        this(context, key, context.getResources().getString(titleRes));
    }

    @Override
    protected void logic() {
        root.setClickable(false);
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_catalog;
    }

}
