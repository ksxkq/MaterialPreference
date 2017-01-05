package com.ksxkq.materialpreference.preferences;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class MaterialPreferenceCatalog extends AbsMaterialPreference {

    private TextView mTitleTv;

    public MaterialPreferenceCatalog(Context context) {
        super(context);
    }

    public MaterialPreferenceCatalog(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void logic() {

    }

    @Override
    protected void findViews() {
        mTitleTv = (TextView) findViewById(R.id.title_tv);
    }

    @Override
    public int getLayout() {
        return R.layout.material_preference_catalog;
    }

    public void setTitle(@StringRes int resid) {
        setTitle(getResources().getString(resid));
    }

    public void setTitle(String titleStr) {
        mTitleTv.setText(titleStr);
    }

}
