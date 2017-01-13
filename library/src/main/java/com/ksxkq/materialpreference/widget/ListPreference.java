package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.view.View;

/**
 * OnePiece
 * Created by xukq on 3/20/16.
 */
public class ListPreference extends ScreenPreference {

    private int mItemNamesRes;
    private int mItemValuesRes;

    public ListPreference(Context context, String key, @ArrayRes int itemNames, @ArrayRes int itemValues) {
        super(context, key);
        mItemNamesRes = itemNames;
        mItemValuesRes = itemValues;

        String summary = dao.getString(key, "");
        setSummary(summary);
    }

    @Override
    protected void setListeners() {
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputModule.showSingleChoiceInput(key, getTitle(), mItemNamesRes, mItemValuesRes, v);
            }
        });
    }

}
