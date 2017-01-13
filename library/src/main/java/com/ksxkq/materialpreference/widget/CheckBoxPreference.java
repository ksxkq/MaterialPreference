package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.ksxkq.materialpreference.R;

/**
 * Created by xukq on 2/27/16.
 */
public class CheckBoxPreference extends BasePreference {

    private CheckBox checkBox;
    private boolean defaultValue = false;

    public CheckBoxPreference(Context context, String key) {
        super(context, key);
    }

    public CheckBoxPreference(Context context, String key, boolean defaultValue) {
        super(context, key);
        this.defaultValue = defaultValue;
        checkBox.setChecked(dao.getBoolean(key, defaultValue));
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_checkbox;
    }

    @Override
    protected void findView() {
        checkBox = (CheckBox) findViewById(R.id.compoundbutton_cb);
    }

    @Override
    protected void logic() {
        checkBox.setChecked(dao.getBoolean(key, defaultValue));
    }

    @Override
    protected void setListeners() {
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
                onPreferenceCallback.onCheckedChanged(key, checkBox, checkBox.isChecked());
            }
        });
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        titleTv.setEnabled(enabled);
        checkBox.setEnabled(enabled);
//        if (enabled) {
//            titleTv.setTextColor(getResources().getColor(R.color.primary_text));
//        } else {
//            titleTv.setTextColor(getResources().getColor(android.R.color.primary_text_dark_nodisable));
//        }
    }

    public void setChecked(boolean value) {
        checkBox.setChecked(value);
    }

}
