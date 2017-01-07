package com.ksxkq.materialpreference.preferences;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;
import com.ksxkq.materialpreference.utils.ThemeUtils;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceScreenProvider extends BasePreferenceProvider<PreferenceScreen> {

    @Override
    public int getLayoutId() {
        return R.layout.material_preference_screen_and_list;
    }

    @Override
    protected void onRootView(View rootView, BasePreferenceViewHolder holder) {
        final MaterialPreferenceConfig config = MaterialPreferenceConfig.getInstance();
        int color = rootView.getResources().getColor(config.getTheme().getPrimaryColor());
        Drawable tintDrawable = ThemeUtils.tintDrawable(rootView.getResources().getDrawable(R.drawable.chevron_right), color);
        holder.rightIconIv.setImageDrawable(tintDrawable);
        holder.rightSecondIconIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                config.onSecondIconClick(preference.getKey(), view);
            }
        });
    }
}
