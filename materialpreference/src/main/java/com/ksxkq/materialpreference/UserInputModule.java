package com.ksxkq.materialpreference;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.view.View;
import android.widget.SeekBar;

import com.ksxkq.materialpreference.widget.ListPreference;

/**
 * Created by yarolegovich on 05.05.2016.
 */
public interface UserInputModule {

    void showEditTextInput(
            String key,
            CharSequence title,
            CharSequence defaultValue,
            Listener<String> listener);

    void showSingleChoiceInput(
            Context context,
            String key,
            CharSequence title,
            @ArrayRes int displayItems,
            @ArrayRes int values,
            View view,
            ListPreference.OnDismissListener onDismissListener);

    void showSeekbarEditInput(
            Context context,
            String title,
            int progress,
            int max,
            SeekBar.OnSeekBarChangeListener onSeekBarChangeListener
    );

    interface Listener<T> {
        void onInput(T value);
    }
}
