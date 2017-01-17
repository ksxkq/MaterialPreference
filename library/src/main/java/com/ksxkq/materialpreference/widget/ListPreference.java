package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.utils.ArrayUtils;

/**
 * OnePiece
 * Created by xukq on 3/20/16.
 */
public class ListPreference extends ScreenPreference {

    private int mItemNamesRes;
    private int mItemValuesRes;

    public ListPreference(Context context, String key, String title, @ArrayRes int itemNames, @ArrayRes int itemValues) {
        super(context, key, title);
        mItemNamesRes = itemNames;
        mItemValuesRes = itemValues;

        updateSummary(key, itemNames, itemValues);
        rightIcon.setImageResource(R.drawable.chevron_down);
    }

    private void updateSummary(String key, @ArrayRes int itemNames, @ArrayRes int itemValues) {
        String value = dao.getString(key, "");
        String summary = ArrayUtils.getName(value, itemNames, itemValues, getResources());
        setSummary(summary);
    }

    public ListPreference(Context context, String key, @StringRes int titleRes, @ArrayRes int itemNames, @ArrayRes int itemValues) {
        this(context, key, context.getResources().getString(titleRes), itemNames, itemValues);
    }

    @Override
    protected void setListeners() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputModule.showSingleChoiceInput(getContext(), key, getTitle(), mItemNamesRes, mItemValuesRes, v, new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        updateSummary(key, mItemNamesRes, mItemValuesRes);
                    }
                });
            }
        });
    }

    public interface OnDismissListener {
        void onDismiss();
    }


}
