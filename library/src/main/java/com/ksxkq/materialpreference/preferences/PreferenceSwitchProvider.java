package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/6/17.
 */

public class PreferenceSwitchProvider extends BasePreferenceProvider<PreferenceSwitch> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(getLayoutId(), parent, false);
        final PreferenceSwitchViewHolder viewHolder = new PreferenceSwitchViewHolder(root);
        viewHolder.switchSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                MaterialPreferenceConfig preferenceConfig = com.ksxkq.materialpreference.MaterialPreferenceConfig.getInstance();
                preferenceConfig.onCheckedChanged(preference.getKey(), compoundButton, isChecked);
            }
        });
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCheck = viewHolder.switchSw.isChecked();
                viewHolder.switchSw.setChecked(!isCheck);
            }
        });
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, @NonNull Object p) {
        super.onBindViewHolder(h, p);
        PreferenceSwitchViewHolder viewHolder = (PreferenceSwitchViewHolder) h;
        viewHolder.switchSw.setChecked(preference.isChecked());
    }

    @Override
    public int getLayoutId() {
        return R.layout.material_preference_switch;
    }

    private static class PreferenceSwitchViewHolder extends BasePreferenceViewHolder {
        SwitchCompat switchSw;

        PreferenceSwitchViewHolder(View itemView) {
            super(itemView);
            switchSw = (SwitchCompat) itemView.findViewById(R.id.switch_sc);
        }
    }

}
