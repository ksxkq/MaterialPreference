package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/6/17.
 */

public class PreferenceSeekbarProvider extends BasePreferenceProvider<PreferenceSeekbar> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        // 注意：preference 是在 onBindViewHolder 的时候才赋值
        final View root = inflater.inflate(getLayoutId(), parent, false);
        return new PreferenceSeekbarViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, @NonNull final Object p) {
        super.onBindViewHolder(h, p);
        final PreferenceSeekbarViewHolder viewHolder = (PreferenceSeekbarViewHolder) h;
        final PreferenceSeekbar preference = (PreferenceSeekbar) p;
        viewHolder.seekBar.setOnSeekBarChangeListener(null);
        viewHolder.seekBar.setMax(preference.getMax());
        viewHolder.seekBar.setProgress(preference.getValue());
        viewHolder.valueTv.setText(String.valueOf(preference.getValue()));
        final MaterialPreferenceConfig preferenceConfig = com.ksxkq.materialpreference.MaterialPreferenceConfig.getInstance();
        viewHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean isUser) {
                preference.setValue(progress);
                preferenceConfig.onProgressChanged(preference.getKey(), seekBar, progress, isUser);
                viewHolder.valueTv.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                preferenceConfig.onStartTrackingTouch(preference.getKey(), seekBar);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                preferenceConfig.getStorageModule(seekBar.getContext()).putInt(preference.getKey(), seekBar.getProgress());
                preferenceConfig.onStopTrackingTouch(preference.getKey(), seekBar);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.material_preference_seekbar;
    }

    private static class PreferenceSeekbarViewHolder extends BasePreferenceViewHolder {
        SeekBar seekBar;
        TextView valueTv;

        PreferenceSeekbarViewHolder(View itemView) {
            super(itemView);
            seekBar = (SeekBar) itemView.findViewById(R.id.seekbar_sb);
            valueTv = (TextView) itemView.findViewById(R.id.value_tv);
        }
    }
}
