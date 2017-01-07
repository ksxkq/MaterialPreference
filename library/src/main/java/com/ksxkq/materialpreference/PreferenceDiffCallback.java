package com.ksxkq.materialpreference;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.ksxkq.materialpreference.preferences.BasePreference;

import java.lang.reflect.Field;
import java.util.List;

/**
 * OnePiece
 * Created by xukq on 1/6/17.
 */

public class PreferenceDiffCallback extends DiffUtil.Callback {

    private final List<? extends BasePreference> mOldData;
    private final List<? extends BasePreference> mNewData;

    public PreferenceDiffCallback(List<BasePreference> oldList, List<BasePreference> newList) {
        this.mOldData = oldList;
        this.mNewData = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldData != null ? mOldData.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewData != null ? mNewData.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        BasePreference oldPreference = mOldData.get(oldItemPosition);
        BasePreference newPreference = mNewData.get(newItemPosition);
        return TextUtils.equals(oldPreference.getKey(), newPreference.getKey());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        BasePreference oldPreference = mOldData.get(oldItemPosition);
        BasePreference newPreference = mNewData.get(newItemPosition);
        for (Field oldPreField : oldPreference.getClass().getDeclaredFields()) {
            oldPreField.setAccessible(true);
            for (Field newPreField : newPreference.getClass().getDeclaredFields()) {
                // 有出现属性不等的情况，就返回 false
                if (!TextUtils.equals(oldPreField.getName(), newPreField.getName())) {
                    return false;
                }
            }
        }
        return true;
    }
}
