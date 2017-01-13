package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

import com.ksxkq.materialpreference.R;

/**
 * Created by xukq on 1/16/16.
 */
public class SwitchPreference extends BasePreference {

    private SwitchCompat switchCompat;
    private boolean defaultValue;

    public SwitchPreference(Context context, String key, boolean defaultValue) {
        super(context, key);
        this.defaultValue = defaultValue;
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_switch;
    }

    @Override
    protected void findView() {
        switchCompat = (SwitchCompat) findViewById(R.id.compoundbutton_cb);
    }

    @Override
    protected void logic() {
        boolean value = dao.getBoolean(key, defaultValue);
        switchCompat.setChecked(value);
    }

    @Override
    protected void setListeners() {
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCompat.setChecked(!switchCompat.isChecked());
            }
        });
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onPreferenceCallback.onCheckedChanged(key, buttonView, isChecked);
            }
        });
    }

    public void setChecked(boolean value) {
        switchCompat.setChecked(value);
    }

}
