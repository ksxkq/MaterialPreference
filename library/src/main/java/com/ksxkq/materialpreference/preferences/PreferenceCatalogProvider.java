package com.ksxkq.materialpreference.preferences;

import android.view.View;

import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceCatalogProvider extends BasePreferenceProvider<PreferenceCategory> {

    @Override
    public int getLayoutId() {
        return R.layout.material_preference_catalog;
    }

    @Override
    protected void onRootView(View rootView, BasePreferenceViewHolder holder) {
        super.onRootView(rootView, holder);
        rootView.setClickable(false);
    }

}
