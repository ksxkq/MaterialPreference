package com.ksxkq.materialpreference.demo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.ksxkq.materialpreference.MaterialPreferenceManager;
import com.ksxkq.materialpreference.SimpleOnPreferenceCallback;
import com.ksxkq.materialpreference.preferences.BasePreference;
import com.ksxkq.materialpreference.preferences.PreferenceScreen;
import com.ksxkq.materialpreference.utils.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FOR_SUMMARY = 0;
    private MaterialPreferenceManager materialPreferenceManager;
    private SimpleOnPreferenceCallback onPreferenceCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        materialPreferenceManager = new MaterialPreferenceManager(recyclerView);
        materialPreferenceManager
                .addPreferenceCategory("setting_category", "设置")
                .addPreferenceScreen("setting", "应用设置")
                .addPreferenceScreen("sc1", "其它设置")
                .addPreferenceSeekbar("sb1", "亮度设置", 0, 100)
                .addPreferenceCheckbox("cb1", "CheckBox", true)
                .addPreferenceSwitch("sw1", "开关", true)
                .addPreferenceList("list1", "列表", R.array.sensitivity_names, R.array.sensitivity_values)
                .apply();

        onPreferenceCallback = new SimpleOnPreferenceCallback() {
            @Override
            public void onPreferenceClick(String key, View view) {
                if (TextUtils.equals("setting", key)) {
                    Intent intent = new Intent(MainActivity.this, ActivitySetting.class);
                    intent.putExtra("key", key);
                    startActivityForResult(intent, REQUEST_CODE_FOR_SUMMARY);
                }
            }
        };
        materialPreferenceManager.registerCallback(onPreferenceCallback);

        // 自定义图标位置二
        PreferenceScreen customSecondIconScr = new PreferenceScreen("customSecondIconScr", "customSecondIconScr");
        final int color = getResources().getColor(R.color.material_preference_summary);
        customSecondIconScr.setRightSecondIconDrawable(ThemeUtils.tintDrawable(this, R.drawable.information_outline, color));
        materialPreferenceManager.addPreference(customSecondIconScr);

        //自定义图标位置一
        PreferenceScreen customRightIconScr = new PreferenceScreen("customSecondIconScr", "customSecondIconScr");
        customRightIconScr.setRightIconDrawable(ThemeUtils.tintDrawable(this, R.drawable.information_outline, color));
        materialPreferenceManager.addPreference(customRightIconScr);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        materialPreferenceManager.unregisterCallback(onPreferenceCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_SUMMARY && resultCode == RESULT_OK) {
            String key = data.getStringExtra("key");
            String summary = data.getStringExtra("summary");
            final BasePreference preference = materialPreferenceManager.getPreference(key);
            if (preference != null) {
                preference.setSummary(summary);
                materialPreferenceManager.updatePreference(preference);
            }
        }
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
        Drawable tintDrawable = ThemeUtils.tintDrawable(getResources().getDrawable(R.drawable.information_outline), getResources().getColor(R.color.material_preference_summary));
        BasePreference newPreference = list.get(1);
        newPreference.setTitle("新的 Title");
        newPreference.setRightSecondIconDrawable(tintDrawable);
        materialPreferenceManager.updatePreference(newPreference);
    }
}
