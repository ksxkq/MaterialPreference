package com.ksxkq.materialpreference.preferences;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;

import me.drakeet.multitype.ItemViewProvider;


/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public abstract class BasePreferenceProvider<T extends BasePreference> extends ItemViewProvider {

    protected T preference;

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, @NonNull Object p) {
        this.preference = (T) p;
        BasePreferenceViewHolder holder = (BasePreferenceViewHolder) h;
        BasePreference basePreference = (BasePreference) p;
        holder.titleTv.setText(basePreference.getTitle());
        // 子类去填充子类需要的
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        // 注意：preference 是在 onBindViewHolder 的时候才赋值
        View root = inflater.inflate(getLayoutId(), parent, false);
        BasePreferenceViewHolder holder = new BasePreferenceViewHolder(root);
        // item 默认可以点击，如果要禁用，到子类禁用
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialPreferenceConfig.getInstance().onClick(preference.getKey(), view);
            }
        });
        onRootView(root);
        return holder;
    }

    public abstract int getLayoutId();

    /**
     * 子类可以在这个回调中，重新设置控件的监听事件
     *
     * @param rootView item
     */
    protected void onRootView(View rootView) {

    }

}
