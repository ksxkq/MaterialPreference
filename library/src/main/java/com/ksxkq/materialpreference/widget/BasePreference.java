package com.ksxkq.materialpreference.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.OnPreferenceCallback;
import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.StorageModule;
import com.ksxkq.materialpreference.UserInputModule;
import com.ksxkq.materialpreference.utils.Utils;

/**
 * Created by xukq on 1/16/16.
 */
public abstract class BasePreference extends LinearLayout {

    public TextView titleTv;
    protected View root;
    protected String key;
    protected StorageModule dao;
    protected UserInputModule userInputModule;
    private Context context;
    protected OnPreferenceCallback onPreferenceCallback;

    public BasePreference(Context context, String key, String title) {
        super(context);
        this.context = context;
        this.key = key;
        init();

        setTitle(title);
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
        setOrientation(HORIZONTAL);
        setId(R.id.root);
        dao = MaterialPreferenceConfig.getInstance().getStorageModule(context);
        userInputModule = MaterialPreferenceConfig.getInstance().getUserInputModule(context);
        onPreferenceCallback = MaterialPreferenceConfig.getInstance();

        LayoutInflater.from(context).inflate(getLayout(), this);
        root = findViewById(R.id.root);
        titleTv = (TextView) findViewById(R.id.title_tv);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPreferenceCallback.onPreferenceClick(key, v);
            }
        });
        findView();
        onConfigureSelf();
        setRippleBackground();
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

    protected void setRippleBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && root != null && root.getBackground() != null) {
            setOnTouchListener(new OnTouchListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    root.getBackground().setHotspot(event.getX(), event.getY());
                    return false;
                }
            });
        }
    }

    protected void onConfigureSelf() {
        setBackgroundResource(R.drawable.bg_material_preference);
        int padding = Utils.dpToPixels(getContext(), 16);
        setPadding(padding, 0, padding, 0);
        setGravity(Gravity.CENTER_VERTICAL);
        setClickable(true);
        int height = (int) getResources().getDimension(R.dimen.material_preference_height);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        setLayoutParams(params);
    }

}
