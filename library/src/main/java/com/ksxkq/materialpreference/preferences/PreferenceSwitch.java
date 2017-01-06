package com.ksxkq.materialpreference.preferences;

/**
 * OnePiece
 * Created by xukq on 1/6/17.
 */

public class PreferenceSwitch extends BasePreference {
    private boolean isChecked;

    public PreferenceSwitch(String key, String title, boolean isChecked) {
        super(key, title);
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
