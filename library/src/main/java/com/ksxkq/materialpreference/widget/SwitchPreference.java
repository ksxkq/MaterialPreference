package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.CompoundButton;

import com.ksxkq.materialpreference.R;

/**
 * Created by xukq on 1/16/16.
 */
public class SwitchPreference extends BasePreference {

    private CompoundButton switchCompat;
    private boolean defaultValue;

    public SwitchPreference(Context context, String key, String title, boolean defaultValue) {
        super(context, key, title);
        this.defaultValue = defaultValue;
    }

    public SwitchPreference(Context context, String key, @StringRes int titleRes, boolean defaultValue) {
        this(context, key, context.getResources().getString(titleRes), defaultValue);
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_switch;
    }

    @Override
    protected void findView() {
        switchCompat = (CompoundButton) findViewById(R.id.compoundbutton_cb);
    }

    @Override
    protected void logic() {
        boolean value = dao.getBoolean(key, defaultValue);
        switchCompat.setChecked(value);
    }

    @Override
    protected void setListeners() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCompat.setChecked(!switchCompat.isChecked());
            }
        });
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dao.putBoolean(key,isChecked);
                onPreferenceCallback.onCheckedChanged(key, buttonView, isChecked);
            }
        });
    }

    public void setChecked(boolean value) {
        switchCompat.setChecked(value);
    }

}
