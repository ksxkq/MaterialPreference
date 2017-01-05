package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceScreenProvider extends PreferenceProvider {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.material_preference_screen, parent, false);
        return new ViewHolder(root);
    }

    private static class ViewHolder extends PreferenceViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
