package com.ksxkq.materialpreference.preferences;

/**
 * OnePiece
 * Created by xukq on 1/6/17.
 */

public class PreferenceSeekbar extends BasePreference {

    private int min;
    private int max;
    private int value;

    public PreferenceSeekbar(String key, String title, int value, int min, int max) {
        super(key, title);
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
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
