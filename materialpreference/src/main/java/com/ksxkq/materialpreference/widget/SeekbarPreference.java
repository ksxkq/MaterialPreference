package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;

import static com.ksxkq.materialpreference.R.id.value_tv;

/**
 * Created by xukq on 1/16/16.
 */
public class SeekbarPreference extends BasePreference {

    public static final int MAX = 100;

    public SeekBar seekBar;
    public TextView valueTv;
    private int defaultValue;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;

    public SeekbarPreference(Context context, final String key, String title, int defaultValue, int max) {
        super(context, key, title);
        this.defaultValue = defaultValue;
        if (max == 0) max = MAX;
        seekBar.setMax(max);
        int progress = dao.getInt(key, defaultValue);
        seekBar.setProgress(progress);
        valueTv.setText(String.valueOf(progress));

        mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onPreferenceCallback.onProgressChanged(key, seekBar, progress, fromUser);
                valueTv.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                onPreferenceCallback.onStartTrackingTouch(key, seekBar);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SeekbarPreference.this.seekBar.setProgress(seekBar.getProgress());
                dao.putInt(key, seekBar.getProgress());
                onPreferenceCallback.onStopTrackingTouch(key, seekBar);
            }
        };
        seekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    }

    public SeekbarPreference(Context context, String key, @StringRes int titleRes, int defaultValue, int max) {
        this(context, key, context.getResources().getString(titleRes), defaultValue, max);
    }

    @Override
    protected void logic() {

    }

    @Override
    protected void setListeners() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialPreferenceConfig.getInstance().getUserInputModule(getContext())
                        .showSeekbarEditInput(getContext(), getTitle(), seekBar.getProgress(), seekBar.getMax(), mOnSeekBarChangeListener);
            }
        });
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_seekbar;
    }

    @Override
    protected void findView() {
        seekBar = (SeekBar) findViewById(R.id.seekbar_sb);
        valueTv = (TextView) findViewById(value_tv);
    }

    public void setMax(int max) {
        seekBar.setMax(max);
    }

    public void setProgress(int progress) {
        seekBar.setProgress(progress);
    }

    public int getDefaultValue() {
        return defaultValue;
    }

}
