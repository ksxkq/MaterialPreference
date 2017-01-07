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

public class PreferenceListProvider extends BasePreferenceProvider<PreferenceList> {

    @Override
    public int getLayoutId() {
        return R.layout.material_preference_screen_and_list;
    }

    @Override
    public void onRootView(View rootView, BasePreferenceViewHolder holder) {
        final MaterialPreferenceConfig config = MaterialPreferenceConfig.getInstance();
        int color = config.getTheme().getPrimaryColor();
        Drawable tintDrawable = ThemeUtils.tintDrawable(rootView.getResources().getDrawable(R.drawable.chevron_down), color);
        holder.rightIconIv.setImageDrawable(tintDrawable);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialPreferenceConfig.getInstance().getUserInputModule(view.getContext()).showSingleChoiceInput(
                        preference.getKey(),
                        preference.getTitle(),
                        preference.getNamesRes(),
                        preference.getValuesRes(),
                        view
                );
            }
        });
        holder.rightSecondIconIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                config.onSecondIconClick(preference.getKey(), view);
            }
        });
    }

}
