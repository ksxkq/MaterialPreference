package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;

import me.drakeet.multitype.ItemViewProvider;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceCatalogProvider extends ItemViewProvider<PreferenceCategory, PreferenceCatalogProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.material_preference_catalog, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PreferenceCategory preferenceCatalog) {
        holder.itemView.setTag(R.id.key, preferenceCatalog.getKey());
        holder.setTitle(preferenceCatalog.getTitle());
    }

    static class ViewHolder extends PreferenceViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
