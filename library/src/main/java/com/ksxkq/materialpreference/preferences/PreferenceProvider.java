package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import me.drakeet.multitype.ItemViewProvider;


/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public abstract class PreferenceProvider<T> extends ItemViewProvider {

    protected T preference;

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, @NonNull Object p) {
        this.preference = (T) p;
        PreferenceViewHolder holder = (PreferenceViewHolder) h;
        Preference preference = (Preference) p;
        holder.setTitle(preference.getTitle());
    }
}
