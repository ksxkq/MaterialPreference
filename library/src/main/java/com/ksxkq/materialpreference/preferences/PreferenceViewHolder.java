package com.ksxkq.materialpreference.preferences;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitleTv;

    public PreferenceViewHolder(View itemView) {
        super(itemView);
        mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
    }

    public void setTitle(@StringRes int titleRes) {
        mTitleTv.setText(titleRes);
    }

    public void setTitle(String titleStr) {
        mTitleTv.setText(titleStr);
    }

}
