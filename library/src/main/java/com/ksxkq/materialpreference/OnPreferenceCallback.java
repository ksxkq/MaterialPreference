package com.ksxkq.materialpreference;

import android.view.View;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public interface OnPreferenceCallback {

    void onClick(String key, View view);

    void onSingleChoice(String key, String name, String value, View view);

}
