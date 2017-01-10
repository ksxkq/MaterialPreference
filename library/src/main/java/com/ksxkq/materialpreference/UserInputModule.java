package com.ksxkq.materialpreference;

import android.support.annotation.ArrayRes;
import android.view.View;

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

    interface Listener<T> {
        void onInput(T value);
    }
}
