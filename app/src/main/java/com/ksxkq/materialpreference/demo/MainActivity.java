package com.ksxkq.materialpreference.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.ksxkq.materialpreference.SimpleOnPreferenceCallback;
import com.ksxkq.materialpreference.widget.PreferenceContainer;
import com.ksxkq.materialpreference.widget.ScreenPreference;

import static com.ksxkq.materialpreference.demo.R.id.container;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FOR_SUMMARY = 0;

    PreferenceContainer mContainer;
    private SimpleOnPreferenceCallback onPreferenceCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        mContainer = (PreferenceContainer) findViewById(container);
        mContainer
                .addCategoryPreference("cate_switch", "Switch")
                .addSwitchPreference("switch_main", "Switch", true)
                .addCheckBoxPreference("checkbox_main", "CheckBox", true)
                .addCategoryPreference("cate_seekbar", "Seekbar")
                .addSeekbarPreference("seekbar_transparency", "Transparency", 0, 100)
                .addCategoryPreference("cate_screen", "Screen")
                .addScreenPreference("setting", "Setting")
                .addCategoryPreference("cate_list", "List")
                .addListPreference("list_sensitivity", "Sensitivity", R.array.sensitivity_names, R.array.sensitivity_names);

        onPreferenceCallback = new SimpleOnPreferenceCallback() {
            @Override
            public void onPreferenceClick(String key, View view) {
                if (TextUtils.equals("setting", key)) {
                    Intent intent = new Intent(MainActivity.this, ActivitySetting.class);
                    intent.putExtra("key", key);
                    startActivityForResult(intent, REQUEST_CODE_FOR_SUMMARY);
                } else {
                    Toast.makeText(MainActivity.this, key + " is click", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, key + "InfoButton is click", Toast.LENGTH_SHORT).show();
            }
        };
        mContainer.registerCallback(onPreferenceCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContainer.unregisterCallback(onPreferenceCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_SUMMARY && resultCode == RESULT_OK) {
            String key = data.getStringExtra("key");
            String summary = data.getStringExtra("summary");
            final ScreenPreference preference = mContainer.getPreference(key);
            if (preference != null) {
                preference.setSummary(summary);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_setting, menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int i = item.getItemId();
                switch (i) {
                    case R.id.add:
                        if (!mContainer.contains("NewScr")) {
                            ScreenPreference newPreference = new ScreenPreference(MainActivity.this, "NewScr", "New Screen");
                            mContainer.addPreference(newPreference);
                        }
                        // 也可以同时添加多个
                        // mContainer.addPreferences(List preferenceList);
                        break;
                    case R.id.remove:
                        mContainer.removePreference("NewScr");
                        // 同时删除多个
                        // mContainer.removePreferences(List preferenceList);
                        break;
                    case R.id.add_info_btn:
                        ScreenPreference infoButtonScr = new ScreenPreference(MainActivity.this, "InfoButtonScr", "InfoButton Screen");
                        // 设置 Info 按钮生效，可以在回调的 onInfoIconClick() 方法中监听
                        infoButtonScr.setInfoButtonClickable();
                        mContainer.addPreference(infoButtonScr);
                        break;
                }
                return true;
            }
        });
        return true;
    }

}
