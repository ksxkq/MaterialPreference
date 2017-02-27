package com.ksxkq.materialpreference;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;

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

    @Override
    public void showSeekbarEditInput(Context context, String title, int progress, final int max, final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        final int initProgress = progress;
        View seekbarEditView = LayoutInflater.from(context).inflate(R.layout.material_preference_seekbar_edit_dialog, null);
        final SeekBar seekBar = (SeekBar) seekbarEditView.findViewById(R.id.seekbar_sb);
        seekBar.setMax(max);
        seekBar.setProgress(progress);
        final EditText editText = (EditText) seekbarEditView.findViewById(R.id.value_et);
        editText.setText(String.valueOf(progress));
        editText.setSelection(editText.getText().toString().length());
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 获取焦点后延时弹出输入法
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager inputManager = (InputMethodManager) editText
                                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.showSoftInput(editText, 0);
                    }
                }, 150);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onSeekBarChangeListener.onProgressChanged(seekBar, progress, fromUser);
                editText.setText(String.valueOf(progress));
                editText.setSelection(editText.getText().toString().length());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                onSeekBarChangeListener.onStartTrackingTouch(seekBar);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onSeekBarChangeListener.onStopTrackingTouch(seekBar);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = builder.setView(seekbarEditView)
                .setTitle(title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String valueStr = editText.getText().toString();
                        int value;
                        try {
                            value = Integer.parseInt(valueStr);
                            value = value > max ? initProgress : value; // 超出了就按默认值算
                        } catch (Exception e) {
                            value = initProgress;
                        }
                        seekBar.setProgress(value);
                        onSeekBarChangeListener.onStopTrackingTouch(seekBar);
                    }
                })
                .create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    private String[] getStrings(@ArrayRes int id) {
        return mContext.getResources().getStringArray(id);
    }
}
