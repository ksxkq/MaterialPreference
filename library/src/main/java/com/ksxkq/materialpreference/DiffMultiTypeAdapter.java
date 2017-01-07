package com.ksxkq.materialpreference;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.ksxkq.materialpreference.preferences.BasePreference;

import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * OnePiece
 * Created by xukq on 1/6/17.
 */

public class DiffMultiTypeAdapter extends MultiTypeAdapter {


    public DiffMultiTypeAdapter(@NonNull List<BasePreference> items) {
        super(items);
    }

    public void setItems(List newItems) {
        final PreferenceDiffCallback diffCallback = new PreferenceDiffCallback((List<BasePreference>) items, newItems);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, true);
        items.clear();
        items.addAll(newItems);
        diffResult.dispatchUpdatesTo(this);
    }

}
