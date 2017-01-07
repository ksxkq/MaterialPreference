package com.ksxkq.materialpreference.preferences;

/**
 * OnePiece
 * Created by xukq on 1/6/17.
 */

public class PreferenceSeekbar extends BasePreference {

    private int max;
    private int value;

    public PreferenceSeekbar(String key, String title, int value, int max) {
        super(key, title);
        this.value = value;
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
