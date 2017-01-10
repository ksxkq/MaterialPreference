package com.ksxkq.materialpreference;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.ksxkq.materialpreference.utils.ArrayUtils;

import java.util.Set;


/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class DefaultUserInputModule implements UserInputModule {

    private Context mContext;

    public DefaultUserInputModule(Context context) {
        this.mContext = context;
    }

    @Override
    public void showEditTextInput(String key, CharSequence title, CharSequence defaultValue, Listener<String> listener) {

    }

    @Override
    public void showSingleChoiceInput(final String key, CharSequence title, @ArrayRes final int nameRes, @ArrayRes final int valuesRes, final View view) {
        String value = MaterialPreferenceConfig.getInstance().getStorageModule(mContext).getString(key, "");
        int selected = ArrayUtils.getPosition(value, getStrings(valuesRes));
        new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setSingleChoiceItems(nameRes, selected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String[] values = mContext.getResources().getStringArray(valuesRes);
                        final String[] names = mContext.getResources().getStringArray(nameRes);

                        String selectedValue = values[which];
                        // 保存
                        MaterialPreferenceConfig.getInstance().getStorageModule(mContext).putString(key, selectedValue);
                        // 回调
                        MaterialPreferenceConfig.getInstance().onSingleChoice(key, names[which], selectedValue, view);

                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void showMultiChoiceInput(String key, CharSequence title, CharSequence[] displayItems, CharSequence[] values, boolean[] defaultSelection, Listener<Set<String>> listener) {

    }

    @Override
    public void showColorSelectionInput(String key, CharSequence title, int defaultColor, Listener<Integer> color) {

    }

    private String[] getStrings(@ArrayRes int id) {
        return mContext.getResources().getStringArray(id);
    }
}
