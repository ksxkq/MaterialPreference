package com.ksxkq.materialpreference.preferences;

import android.graphics.drawable.Drawable;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class BasePreference {

    private String key;
    private String title;
    private String summary;
    private Drawable rightIconDrawable;
    private Drawable rightSecondIconDrawable;

    public BasePreference(String key, String title) {
        setKey(key);
        setTitle(title);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Drawable getRightSecondIconDrawable() {
        return rightSecondIconDrawable;
    }

    public void setRightSecondIconDrawable(Drawable rightSecondIconDrawable) {
        this.rightSecondIconDrawable = rightSecondIconDrawable;
    }

    public Drawable getRightIconDrawable() {
        return rightIconDrawable;
    }

    public void setRightIconDrawable(Drawable rightIconDrawable) {
        this.rightIconDrawable = rightIconDrawable;
    }
}
