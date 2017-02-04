package com.ksxkq.materialpreference.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import com.ksxkq.materialpreference.SimpleOnPreferenceCallback;
import com.ksxkq.materialpreference.widget.PreferenceContainer;
import com.ksxkq.materialpreference.widget.ScreenPreference;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FOR_SUMMARY = 0;

    PreferenceContainer container;
    private SimpleOnPreferenceCallback onPreferenceCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (PreferenceContainer) findViewById(R.id.container);
        container
                .addCategoryPreference("", "开关")
                .addSwitchPreference("switch", "开关", true)
                .addCategoryPreference("", "外观")
                .addScreenPreference("aa", "图标")
                .addLine()
                .addSeekbarPreference("bb", "大小", 0, 100)
                .addLine()
                .addSeekbarPreference("cc", "透明度", 0, 100)
                .addCategoryPreference("", "其它设置")
                .addScreenPreference("a1a", "消息提示")
                .addScreenPreference("a2a", "编辑菜单")
                .addListPreference("sensitivity", R.string.title_sensitivity, R.array.sensitivity_names, R.array.sensitivity_names)
                .addScreenPreference("setting", "更多设置");

        container.getPreferenceMap().get("switch").setInfoButtonClickable();
        ((ScreenPreference)container.getPreference("setting")).leftIconIv.setVisibility(View.VISIBLE);
        ((ScreenPreference)container.getPreference("setting")).leftIconIv.setImageResource(R.drawable.information_outline);

        onPreferenceCallback = new SimpleOnPreferenceCallback() {
            @Override
            public void onPreferenceClick(String key, View view) {
                if (TextUtils.equals("setting", key)) {
                    Intent intent = new Intent(MainActivity.this, ActivitySetting.class);
                    intent.putExtra("key", key);
                    startActivityForResult(intent, REQUEST_CODE_FOR_SUMMARY);
                }
            }

            @Override
            public void onCheckedChanged(String key, CompoundButton compoundButton, boolean isChecked) {
                super.onCheckedChanged(key, compoundButton, isChecked);
            }

            @Override
            public void onSingleChoice(String key, String name, String value, View view) {
                super.onSingleChoice(key, name, value, view);
            }

            @Override
            public void onSecondIconClick(String key, View view) {
                super.onSecondIconClick(key, view);
            }

            @Override
            public void onInfoIconClick(String key, String title, View view) {
                super.onInfoIconClick(key, title, view);
            }
        };
        container.registerCallback(onPreferenceCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        container.unregisterCallback(onPreferenceCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_SUMMARY && resultCode == RESULT_OK) {
            String key = data.getStringExtra("key");
            String summary = data.getStringExtra("summary");
            final ScreenPreference preference = container.getPreference(key);
            if (preference != null) {
                preference.setSummary(summary);
            }
        }
    }

    public void add(View view) {
//        PreferenceScreen newPreference = new PreferenceScreen("newScr", "新增 Screen");
//        materialPreferenceManager.appendPreferenceBehindKey("setting", newPreference);
    }

    public void remove(View view) {
//        materialPreferenceManager.removePreference("newScr");
    }

//    private List<BasePreference> newList = new ArrayList<>();

    public void addList(View view) {
//        PreferenceScreen newPreference = new PreferenceScreen("newPreference1", "newPreference1");
//        PreferenceScreen newPreference2 = new PreferenceScreen("newPreference2", "newPreference2");
//        PreferenceScreen newPreference3 = new PreferenceScreen("newPreference3", "newPreference3");
//        newList.add(newPreference);
//        newList.add(newPreference2);
//        newList.add(newPreference3);
//        materialPreferenceManager.addPreferences(newList);
    }

//    List<BasePreference> list = new ArrayList<>();

    public void updateList(View view) {
//        PreferenceScreen newPreference = new PreferenceScreen("updateList1", "updateScr");
//        PreferenceScreen newPreference2 = new PreferenceScreen("updateList2", "updateScr2");
//        PreferenceScreen newPreference3 = new PreferenceScreen("updateList3", "updateScr3");
//        list.add(newPreference);
//        list.add(newPreference2);
//        list.add(newPreference3);
//        materialPreferenceManager.updatePreferences(list);
    }

    public void updateContent(View view) {
//        Drawable tintDrawable = ThemeUtils.tintDrawable(getResources().getDrawable(R.drawable.information_outline), getResources().getColor(R.color.material_preference_summary));
//        BasePreference newPreference = list.get(1);
//        newPreference.setTitle("新的 Title");
//        newPreference.setRightSecondIconDrawable(tintDrawable);
//        materialPreferenceManager.updatePreference(newPreference);
    }
}
