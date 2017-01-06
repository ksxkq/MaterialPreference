package com.ksxkq.materialpreference;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public interface OnPreferenceCallback {

    void onClick(String key, View view);

    void onSingleChoice(String key, String name, String value, View view);

    void onProgressChanged(String key, SeekBar seekBar, int progress, boolean isUser);

    void onStartTrackingTouch(String key, SeekBar seekBar);

    void onStopTrackingTouch(String key, SeekBar seekBar);

    void onCheckedChanged(String key, CompoundButton compoundButton, boolean isChecked);

}
