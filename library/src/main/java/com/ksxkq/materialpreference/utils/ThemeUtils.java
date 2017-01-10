package com.ksxkq.materialpreference.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.TintTypedArray;
import android.widget.ImageView;

import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/7/17.
 */

public class ThemeUtils {

    private static final int[] TEMP_ARRAY = new int[1];

    public static int getThemeAttrColor(Context context, int attr) {
        TEMP_ARRAY[0] = attr;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, null, TEMP_ARRAY);
        try {
            return a.getColor(0, 0);
        } finally {
            a.recycle();
        }
    }

    public static void tintImageView(ImageView imageView, @ColorRes int color) {
        imageView.setColorFilter(imageView.getResources().getColor(color));
    }

    public static Drawable tintDrawable(Drawable drawable, @ColorInt int color) {
        if (drawable == null) {
            return null;
        }

        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }

    public static Drawable tintDrawable(Context context, @DrawableRes int drawableRes, @ColorInt int color) {
        Drawable drawable = context.getResources().getDrawable(drawableRes);

        if (drawable == null) {
            return null;
        }

        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }
}
