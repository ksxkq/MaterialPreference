package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.widget.SeekBar;

import com.ksxkq.materialpreference.R;

/**
 * Created by xukq on 1/16/16.
 */
public class SeekbarPreference extends BasePreference {

    public static final int MAX = 100;

    private SeekBar mSeekBar;
    private int defaultValue;

    public SeekbarPreference(Context context, String key) {
        super(context, key);
    }

    public SeekbarPreference(Context context, String key, int max) {
        super(context, key);
        if (max == 0) max = MAX;
        mSeekBar.setMax(max);
        int progress = dao.getInt(key, 0);
        mSeekBar.setProgress(progress);
    }

    public SeekbarPreference(Context context, String key, int defaultValue, int max) {
        super(context, key);
        this.defaultValue = defaultValue;
        if (max == 0) max = MAX;
        mSeekBar.setMax(max);
        int progress = dao.getInt(key, defaultValue);
        mSeekBar.setProgress(progress);
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_seekbar;
    }

    @Override
    protected void findView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekbar_sb);
    }

    @Override
    protected void setListeners() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onPreferenceCallback.onProgressChanged(key, seekBar, progress, fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                onPreferenceCallback.onStartTrackingTouch(key, seekBar);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onPreferenceCallback.onStopTrackingTouch(key, seekBar);
            }
        });
    }

    @Override
    protected void logic() {

    }

    public void setMax(int max) {
        mSeekBar.setMax(max);
    }

    public void setProgress(int progress) {
        mSeekBar.setProgress(progress);
    }

    public int getDefaultValue() {
        return defaultValue;
    }
}
