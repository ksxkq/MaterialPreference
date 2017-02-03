package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.CompoundButton;

import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 2/3/17.
 */

abstract class CompoundButtonPreference extends BasePreference {

    private CompoundButton switchCompat;

    public CompoundButtonPreference(Context context, final String key, String title, boolean defaultValue) {
        super(context, key, title);
        boolean value = dao.getBoolean(key, defaultValue);
        switchCompat.setChecked(value);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCompat.setChecked(!switchCompat.isChecked());
            }
        });
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dao.putBoolean(key, isChecked);
                onPreferenceCallback.onCheckedChanged(key, buttonView, isChecked);
            }
        });
    }

    public CompoundButtonPreference(Context context, String key, @StringRes int titleRes, boolean defaultValue) {
        this(context, key, context.getResources().getString(titleRes), defaultValue);
    }

    @Override
    protected void findView() {
        switchCompat = (CompoundButton) findViewById(R.id.compoundbutton_cb);
    }

    public void setChecked(boolean value) {
        switchCompat.setChecked(value);
    }

}