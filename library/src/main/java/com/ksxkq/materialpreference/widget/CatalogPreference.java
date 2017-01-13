package com.ksxkq.materialpreference.widget;

import android.content.Context;

import com.ksxkq.materialpreference.R;

/**
 * Created by xukq on 1/16/16.
 */
public class CatalogPreference extends BasePreference {


    public CatalogPreference(Context context, String key) {
        super(context, key);
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
