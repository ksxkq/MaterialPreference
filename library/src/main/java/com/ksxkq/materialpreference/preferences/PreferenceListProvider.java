package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceListProvider extends PreferenceProvider<PreferenceList> {

    @NonNull
    @Override
    protected PreferenceViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        // TODO: 1/5/17 layout 要重新写
        View root = inflater.inflate(R.layout.material_preference_screen, parent, false);
        PreferenceViewHolder holder = new PreferenceViewHolder(root);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialPreferenceConfig.getInstance().getUserInputModule(view.getContext()).showSingleChoiceInput(
                        preference.getKey(),
                        preference.getTitle(),
                        preference.getNamesRes(),
                        preference.getValuesRes(),
                        view
                );
            }
        });
        return holder;
    }

}
