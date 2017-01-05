package com.ksxkq.materialpreference.preferences;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.StorageModule;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public abstract class AbsMaterialPreference extends FrameLayout {

    private StorageModule mStorageModule;
    private OnClickListener mOnClickListener;
    private int layout;

    public AbsMaterialPreference(Context context) {
        super(context);
        init();
    }

    public AbsMaterialPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mStorageModule = MaterialPreferenceConfig.getInstance().getStorageModule();
        LayoutInflater.from(getContext()).inflate(getLayout(), this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && root.getBackground() != null) {
//            setOnTouchListener(new OnTouchListener() {
//                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    root.getBackground().setHotspot(event.getX(), event.getY());
//                    return false;
//                }
//            });
//        }

        findViews();
        logic();
    }

    protected abstract void logic();

    protected abstract void findViews();

    public void setOnPreferenceOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public abstract int getLayout();

}
