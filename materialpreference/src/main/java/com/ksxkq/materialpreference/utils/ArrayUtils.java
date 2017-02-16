package com.ksxkq.materialpreference.utils;

import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.text.TextUtils;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class ArrayUtils {

    public static String getName(String value, @ArrayRes int namesRes, @ArrayRes int valuesRes, Resources resources) {
        int selectedIndex = getPosition(value, resources.getStringArray(valuesRes));
        return resources.getStringArray(namesRes)[selectedIndex];
    }

    public static int getPosition(String value, String[] values) {
        int position = 0;
        for (int i = 0; i < values.length; i++) {
            if (TextUtils.equals(value, values[i])) {
                return i;
            }
        }
        return position;
    }

}
