package com.ksxkq.materialpreference;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.view.View;

import java.util.Set;

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
            String key,
            CharSequence title,
            @ArrayRes int displayItems,
            @ArrayRes int values,
            View view);

    void showMultiChoiceInput(
            String key,
            CharSequence title,
            CharSequence[] displayItems,
            CharSequence[] values,
            boolean[] defaultSelection,
            Listener<Set<String>> listener);

    void showColorSelectionInput(
            String key,
            CharSequence title,
            int defaultColor,
            Listener<Integer> color);

    interface Factory {
        UserInputModule create(Context context);
    }

    interface Listener<T> {
        void onInput(T value);
    }
}
