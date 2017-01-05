package com.ksxkq.materialpreference.utils;

import android.text.TextUtils;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class ArrayUtils {

    public static int getPosition(String key, String[] values) {
        int position = 0;
        for (int i = 0; i < values.length; i++) {
            if (TextUtils.equals(key, values[i])) {
                return i;
            }
        }
        return position;
    }

}
