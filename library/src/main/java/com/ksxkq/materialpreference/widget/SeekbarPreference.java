package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ksxkq.materialpreference.R;

import static com.ksxkq.materialpreference.R.id.value_tv;

/**
 * Created by xukq on 1/16/16.
 */
public class SeekbarPreference extends BasePreference {

    public static final int MAX = 100;

    private SeekBar mSeekBar;
    private TextView mValueTv;
    private int defaultValue;

    public SeekbarPreference(Context context, String key, String title, int defaultValue, int max) {
        super(context, key, title);
        this.defaultValue = defaultValue;
        if (max == 0) max = MAX;
        mSeekBar.setMax(max);
        int progress = dao.getInt(key, defaultValue);
        mSeekBar.setProgress(progress);
        mValueTv.setText(String.valueOf(progress));
    }

    public SeekbarPreference(Context context, String key, @StringRes int titleRes, int defaultValue, int max) {
        this(context, key, context.getResources().getString(titleRes), defaultValue, max);
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_seekbar;
    }

    @Override
    protected void findView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekbar_sb);
        mValueTv = (TextView) findViewById(value_tv);
    }

    @Override
    protected void setListeners() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onPreferenceCallback.onProgressChanged(key, seekBar, progress, fromUser);
                mValueTv.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                onPreferenceCallback.onStartTrackingTouch(key, seekBar);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onPreferenceCallback.onStopTrackingTouch(key, seekBar);
                dao.putInt(key, seekBar.getProgress());
            }
        });
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
