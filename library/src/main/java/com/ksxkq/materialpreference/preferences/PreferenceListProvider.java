package com.ksxkq.materialpreference.preferences;

import android.view.View;

import com.ksxkq.materialpreference.MaterialPreferenceConfig;
import com.ksxkq.materialpreference.R;

/**
 * OnePiece
 * Created by xukq on 1/5/17.
 */

public class PreferenceListProvider extends BasePreferenceProvider<PreferenceList> {

    @Override
    public int getLayoutId() {
        return R.layout.material_preference_list;
    }

    @Override
    public void onRootView(View rootView) {
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
    }

}
