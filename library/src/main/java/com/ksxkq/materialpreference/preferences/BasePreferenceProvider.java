package com.ksxkq.materialpreference.preferences;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;

import me.drakeet.multitype.ItemViewProvider;


/**
 * OnePiece
 * 这里面只负责填充内容
 * Created by xukq on 1/5/17.
 */

public abstract class BasePreferenceProvider<T extends BasePreference> extends ItemViewProvider {

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, @NonNull Object p) {
        h.itemView.setTag(R.id.key, p);
        BasePreference preference = (T) p;
        BasePreferenceViewHolder holder = (BasePreferenceViewHolder) h;
        BasePreference basePreference = (BasePreference) p;
        holder.titleTv.setText(basePreference.getTitle());
        if (holder.summaryTv != null) {
            holder.summaryTv.setText(preference.getSummary());
        }
        if (holder.rightIconIv != null && preference.getRightIconDrawable() != null) {
            final Drawable rightIconDrawable = preference.getRightIconDrawable();
            holder.rightIconIv.setImageDrawable(rightIconDrawable);
            holder.rightIconIv.setVisibility(rightIconDrawable != null ? View.VISIBLE : View.INVISIBLE);
        }
        if (holder.rightSecondIconIv != null && preference.getRightSecondIconDrawable() != null) {
            final Drawable rightSecondIconDrawable = preference.getRightSecondIconDrawable();
            holder.rightSecondIconIv.setImageDrawable(rightSecondIconDrawable);
            holder.rightSecondIconIv.setVisibility(rightSecondIconDrawable != null ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        final View root = inflater.inflate(getLayoutId(), parent, false);
        BasePreferenceViewHolder holder = new BasePreferenceViewHolder(root);
        // item 默认可以点击，如果要禁用，到子类禁用
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasePreference preference = (BasePreference) root.getTag(R.id.key);
                MaterialPreferenceConfig.getInstance().onPreferenceClick(preference.getKey(), view);
            }
        });
        onRootView(root, holder);
        return holder;
    }

    public abstract int getLayoutId();

    /**
     * 子类可以在这个回调中，重新设置控件的监听事件
     */
    protected void onRootView(View rootView, BasePreferenceViewHolder holder) {

    }

}
