package com.ksxkq.materialpreference.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

/**
 * OnePiece
 * Created by xukq on 1/7/17.
 */

public class ThemeUtils {

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
}
