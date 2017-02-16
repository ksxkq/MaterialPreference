package com.ksxkq.materialpreference;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.view.View;

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

    interface Listener<T> {
        void onInput(T value);
    }
}
