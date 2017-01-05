package com.ksxkq.materialpreference.preferences;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitleTv;

    public PreferenceViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = (String) view.getTag(R.id.key);
                MaterialPreferenceConfig.getInstance().onClick(key, view);
            }
        });

        mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
    }

    public void setTitle(@StringRes int titleRes) {
        mTitleTv.setText(titleRes);
    }

    public void setTitle(String titleStr) {
        mTitleTv.setText(titleStr);
    }

}
