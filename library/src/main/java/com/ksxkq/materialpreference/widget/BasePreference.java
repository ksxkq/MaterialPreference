package com.ksxkq.materialpreference.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.OnPreferenceCallback;
import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.StorageModule;
import com.ksxkq.materialpreference.UserInputModule;

/**
 * Created by xukq on 1/16/16.
 */
public abstract class BasePreference extends FrameLayout {

    public static final String SUMMARY = "_summary";

    protected TextView titleTv;
    protected View root;
    protected String key;
    protected StorageModule dao;
    protected UserInputModule userInputModule;
    private Context context;
    protected OnPreferenceCallback onPreferenceCallback;

    public BasePreference(Context context, String key) {
        super(context);
        this.context = context;
        this.key = key;
        init();
    }

    public String getKey() {
        return key;
    }

    public void setTitle(@StringRes int titleRes) {
        setTitle(getResources().getString(titleRes));
    }

    public void setTitle(String title) {
        titleTv.setText(title);
    }

    public void setTitleColor(@ColorRes int colorRes) {
        titleTv.setTextColor(getResources().getColor(colorRes));
    }

    public String getTitle() {
        return titleTv.getText().toString();
    }

    private void init() {
        dao = MaterialPreferenceConfig.getInstance().getStorageModule(context);
        userInputModule = MaterialPreferenceConfig.getInstance().getUserInputModule(context);
        onPreferenceCallback = MaterialPreferenceConfig.getInstance();

        LayoutInflater.from(context).inflate(getLayout(), this);
        root = findViewById(R.id.root);
        titleTv = (TextView) findViewById(R.id.title_tv);

        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPreferenceCallback.onPreferenceClick(key, v);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && root.getBackground() != null) {
            setOnTouchListener(new OnTouchListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    root.getBackground().setHotspot(event.getX(), event.getY());
                    return false;
                }
            });
        }

        findView();
        logic();
        setListeners();
    }

    abstract int getLayout();

    protected void findView() {

    }

    protected void logic() {

    }

    protected void setListeners() {

    }

}
