package com.ksxkq.materialpreference;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.ksxkq.materialpreference.utils.ArrayUtils;
import com.ksxkq.materialpreference.widget.ListPreference;


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
    public void showSingleChoiceInput(final Context context, final String key, CharSequence title, @ArrayRes final int nameRes, @ArrayRes final int valuesRes, final View view, final ListPreference.OnDismissListener onDismissListener) {
        String value = MaterialPreferenceConfig.getInstance().getStorageModule(mContext).getString(key, "");
        int selected = ArrayUtils.getPosition(value, getStrings(valuesRes));
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (onDismissListener != null) {
                            onDismissListener.onDismiss();
                        }
                    }
                })
                .setSingleChoiceItems(nameRes, selected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String[] values = context.getResources().getStringArray(valuesRes);
                        final String[] names = context.getResources().getStringArray(nameRes);

                        String selectedValue = values[which];
                        String selectedName = names[which];
                        // 保存
                        MaterialPreferenceConfig.getInstance().getStorageModule(mContext).putString(key, selectedValue);
                        // 回调
                        MaterialPreferenceConfig.getInstance().onSingleChoice(key, selectedName, selectedValue, view);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private String[] getStrings(@ArrayRes int id) {
        return mContext.getResources().getStringArray(id);
    }
}
