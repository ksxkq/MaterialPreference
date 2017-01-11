package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.StorageModule;

/**
 * OnePiece
 * Created by xukq on 1/6/17.
 */

public class PreferenceSwitchProvider extends BasePreferenceProvider<PreferenceSwitch> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        final View root = inflater.inflate(getLayoutId(), parent, false);
        return new PreferenceSwitchViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, @NonNull Object p) {
        super.onBindViewHolder(h, p);
        final PreferenceSwitch preference = (PreferenceSwitch) p;
        final PreferenceSwitchViewHolder viewHolder = (PreferenceSwitchViewHolder) h;
        // 先置空回调，再设置状态，不然会多次回调
        viewHolder.itemView.setOnClickListener(null);
        viewHolder.compoundButton.setOnCheckedChangeListener(null);
        viewHolder.compoundButton.setChecked(preference.isChecked());
        viewHolder.compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                final StorageModule storageModule = MaterialPreferenceConfig.getInstance().getStorageModule(viewHolder.compoundButton.getContext());
                storageModule.putBoolean(preference.getKey(), isChecked);
                MaterialPreferenceConfig preferenceConfig = MaterialPreferenceConfig.getInstance();
                preferenceConfig.onCheckedChanged(preference.getKey(), viewHolder.compoundButton, isChecked);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheck = viewHolder.compoundButton.isChecked();
                viewHolder.compoundButton.setChecked(!isCheck);
            }
        });
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
