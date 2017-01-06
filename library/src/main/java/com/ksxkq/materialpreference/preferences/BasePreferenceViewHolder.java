package com.ksxkq.materialpreference.preferences;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class BasePreferenceViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTv;

    public BasePreferenceViewHolder(View itemView) {
        super(itemView);
        titleTv = (TextView) itemView.findViewById(R.id.title_tv);
    }

}
