package com.ksxkq.materialpreference.preferences;

import android.support.annotation.ArrayRes;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceList extends BasePreference {

    private final int namesRes;
    private final int valuesRes;

    public PreferenceList(String key, String title, @ArrayRes int namesRes, @ArrayRes int valuesRes) {
        super(key, title);
        this.namesRes = namesRes;
        this.valuesRes = valuesRes;
    }

    public int getNamesRes() {
        return namesRes;
    }

    public int getValuesRes() {
        return valuesRes;
    }
}
