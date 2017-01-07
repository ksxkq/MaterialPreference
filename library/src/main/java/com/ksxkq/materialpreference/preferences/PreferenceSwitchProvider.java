package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
        final View root = inflater.inflate(getLayoutId(), parent, false);
        final PreferenceSwitchViewHolder viewHolder = new PreferenceSwitchViewHolder(root);
        viewHolder.compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                BasePreference preference = (BasePreference) root.getTag(R.id.key);
                MaterialPreferenceConfig preferenceConfig = MaterialPreferenceConfig.getInstance();
                preferenceConfig.onCheckedChanged(preference.getKey(), compoundButton, isChecked);
            }
        });
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCheck = viewHolder.compoundButton.isChecked();
                viewHolder.compoundButton.setChecked(!isCheck);
            }
        });
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, @NonNull Object p) {
        super.onBindViewHolder(h, p);
        PreferenceSwitch preference = (PreferenceSwitch) p;
        PreferenceSwitchViewHolder viewHolder = (PreferenceSwitchViewHolder) h;
        viewHolder.compoundButton.setChecked(preference.isChecked());
    }

    @Override
    public int getLayoutId() {
        return R.layout.material_preference_switch;
    }

    private static class PreferenceSwitchViewHolder extends BasePreferenceViewHolder {
        CompoundButton compoundButton;

        PreferenceSwitchViewHolder(View itemView) {
            super(itemView);
            compoundButton = (CompoundButton) itemView.findViewById(R.id.compoundbutton_cb);
        }
    }

}
