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

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    MaterialPreferenceManager materialPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        materialPreferenceManager = new MaterialPreferenceManager(recyclerView);
        materialPreferenceManager
                .addPreferenceCategory("setting", "设置")
                .addPreferenceScreen("sc2", "应用设置")
                .addPreferenceScreen("sc1", "其它设置")
                .addPreferenceSeekbar("sb1", "亮度设置", 0, 100)
                .addPreferenceCheckbox("cb1", "CheckBox", true)
                .addPreferenceSwitch("sw1", "开关", true)
                .addPreferenceList("list1", "列表", R.array.sensitivity_names, R.array.sensitivity_values)
                .apply();
    }

    public void add(View view) {
        PreferenceScreen newPreference = new PreferenceScreen("newScr", "新增 Screen");
        materialPreferenceManager.appendPreferenceBehindKey("setting", newPreference);
    }

    public void remove(View view) {
        materialPreferenceManager.removePreference("newScr");
    }

    private List newList = new ArrayList();

    public void addList(View view) {
        PreferenceScreen newPreference = new PreferenceScreen("newPreference1", "newPreference1");
        PreferenceScreen newPreference2 = new PreferenceScreen("newPreference2", "newPreference2");
        PreferenceScreen newPreference3 = new PreferenceScreen("newPreference3", "newPreference3");
        newList.add(newPreference);
        newList.add(newPreference2);
        newList.add(newPreference3);
        materialPreferenceManager.addPreferences(newList);
    }

    List<BasePreference> list = new ArrayList<>();

    public void updateList(View view) {
        PreferenceScreen newPreference = new PreferenceScreen("updateList1", "updateScr");
        PreferenceScreen newPreference2 = new PreferenceScreen("updateList2", "updateScr2");
        PreferenceScreen newPreference3 = new PreferenceScreen("updateList3", "updateScr3");
        list.add(newPreference);
        list.add(newPreference2);
        list.add(newPreference3);
        materialPreferenceManager.updatePreferences(list);
    }

    public void updateContent(View view) {
        Drawable tintDrawable = ThemeUtils.tintDrawable(getResources().getDrawable(R.drawable.information_outline), getColor(R.color.material_preference_summary));
        BasePreference newPreference = list.get(1);
        newPreference.setTitle("新的 Title");
        newPreference.setRightSecondIconDrawable(tintDrawable);
        materialPreferenceManager.updatePreference(newPreference);
    }
}
