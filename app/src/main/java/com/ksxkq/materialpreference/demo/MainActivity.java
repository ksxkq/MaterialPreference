package com.ksxkq.materialpreference.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ksxkq.materialpreference.MaterialPreferenceManager;
import com.ksxkq.materialpreference.preferences.BasePreference;
import com.ksxkq.materialpreference.preferences.PreferenceScreen;
import com.ksxkq.materialpreference.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MaterialPreferenceManager materialPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        materialPreferenceManager = new MaterialPreferenceManager(recyclerView);
        materialPreferenceManager.addPreferenceCatalog("testKey", "content")
                .addPreferenceCatalog("testKey2", "content2")
                .addPreferenceCatalog("testKey2", "content2")
                .addPreferenceScreen("s1", R.string.app_name)
                .addPreferenceScreen("s2", R.string.app_name)
                .addPreferenceSeekbar("s23", "seekbar", 0, 100)
                .addPreferenceSwitch("s24", "seekbar", true)
                .addPreferenceCheckbox("s25", "seekbar", true)
                .addPreferenceList("11", "list title", R.array.sensitivity_names, R.array.sensitivity_values)
                .apply();


    }

    public void add(View view) {
        PreferenceScreen newPreference = new PreferenceScreen("112211" + System.currentTimeMillis(), "newScr");
        materialPreferenceManager.appendPreferenceBehindKey("testKey2", newPreference);
    }

    public void remove(View view) {
        materialPreferenceManager.removePreference("1111");
    }

    private List newList = new ArrayList();
    public void addList(View view) {
        PreferenceScreen newPreference = new PreferenceScreen("1111" + System.currentTimeMillis(), "listScr");
        PreferenceScreen newPreference2 = new PreferenceScreen("111122" + System.currentTimeMillis(), "listScr2");
        PreferenceScreen newPreference3 = new PreferenceScreen("11112233" + System.currentTimeMillis(), "listScr3");
        newList.add(newPreference);
        newList.add(newPreference2);
        newList.add(newPreference3);
        materialPreferenceManager.addPreferences(newList);
    }

    public void updateList(View view) {
        List<BasePreference> list = new ArrayList<>();
//        PreferenceScreen newPreference = new PreferenceScreen("11111", "updateScr");
//        PreferenceScreen newPreference2 = new PreferenceScreen("1111221", "updateScr2");
//        PreferenceScreen newPreference3 = new PreferenceScreen("111122331", "updateScr3");
//        list.add(newPreference);
//        list.add(newPreference2);
//        list.add(newPreference3);
        BasePreference basePreference = (BasePreference) newList.remove(2);
        Drawable tintDrawable = ThemeUtils.tintDrawable(getResources().getDrawable(R.drawable.information_outline), getColor(R.color.material_preference_summary));
        basePreference.setRightSecondIconDrawable(tintDrawable);
        newList.add(3,basePreference);
        materialPreferenceManager.updatePreferences(newList);
    }
}
