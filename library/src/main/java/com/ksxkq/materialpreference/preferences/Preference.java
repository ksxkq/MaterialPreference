package com.ksxkq.materialpreference.preferences;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class Preference {

    private String key;
    private String title;
    private String summary;

    public Preference() {

    }

    public Preference(String key, String title) {
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

}
