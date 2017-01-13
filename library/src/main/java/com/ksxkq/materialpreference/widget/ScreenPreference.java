package com.ksxkq.materialpreference.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.utils.ThemeUtils;

/**
 * Created by xukq on 1/16/16.
 */
public class ScreenPreference extends BasePreference {

    private static final String SUMMARY = "_summary";

    private TextView summaryTv;
    protected ImageView rightIcon;
    protected ImageView rightSecondIcon;
    private ImageView mLeftIconIv;


    public ScreenPreference(Context context, String key, String title) {
        super(context, key, title);
        rightIcon.setColorFilter(ThemeUtils.getPrimaryColor(getContext()));
        String summary = dao.getString(key + SUMMARY, "");
        summaryTv.setText(summary);
    }

    public ScreenPreference(Context context, String key, @StringRes int titleRes) {
        this(context, key, context.getResources().getString(titleRes));
    }

    @Override
    int getLayout() {
        return R.layout.material_preference_screen_and_list;
    }

    @Override
    protected void findView() {
        summaryTv = (TextView) findViewById(R.id.summary_tv);
        rightIcon = (ImageView) findViewById(R.id.right_icon_iv);
        rightSecondIcon = (ImageView) findViewById(R.id.right_second_icon_iv);
        mLeftIconIv = (ImageView) findViewById(R.id.leftIconIv);
    }

    @Override
    protected void setListeners() {
        rightSecondIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onPreferenceCallback.onSecondIconClick(key, v);
            }
        });
    }

    public void setSummary(String summary) {
        summaryTv.setText(summary);
        dao.putString(key + SUMMARY, summary);
    }

    public void setSummary(@StringRes int summaryRes) {
        setSummary(getResources().getString(summaryRes));
    }

    public void setSummaryColor(int color) {
        summaryTv.setTextColor(color);
    }

    public void setSummarySize(@DimenRes int sizeRes) {
        summaryTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimensionPixelSize(sizeRes));
    }

    public String getSummary() {
        return summaryTv.getText().toString();
    }

    public void setRightIcon(@DrawableRes int rightIconRes) {
        rightIcon.setImageResource(rightIconRes);
        rightIcon.setColorFilter(ThemeUtils.getPrimaryColor(getContext()));
    }

    public void setRightIconColor(@ColorRes int colorRes) {
        rightIcon.setColorFilter(getResources().getColor(colorRes));
    }

    public void setLeftIcon(@DrawableRes int leftIconRes) {
        mLeftIconIv.setImageResource(leftIconRes);
        mLeftIconIv.setColorFilter(ThemeUtils.getPrimaryColor(getContext()));
        mLeftIconIv.setVisibility(VISIBLE);
    }

    public void setLeftIconVisibility(int visibility) {
        mLeftIconIv.setVisibility(visibility);
    }

    public void setRightSecondIcon(Bitmap icon) {
        rightSecondIcon.setImageBitmap(icon);
        rightSecondIcon.setColorFilter(ThemeUtils.getPrimaryColor(getContext()));
        rightSecondIcon.setVisibility(VISIBLE);
    }

    public void setRightSecondIcon(@DrawableRes int iconRes) {
        rightSecondIcon.setImageResource(iconRes);
        rightSecondIcon.setColorFilter(ThemeUtils.getPrimaryColor(getContext()));
        rightSecondIcon.setVisibility(VISIBLE);
    }

}
