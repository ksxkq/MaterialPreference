package com.ksxkq.materialpreference.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.ksxkq.materialpreference.MaterialPreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);

        MaterialPreferenceManager materialPreferenceManager = new MaterialPreferenceManager(recyclerView);
        materialPreferenceManager.addPreferenceCatalog("testKey", "content")
                .addPreferenceCatalog("testKey2", "content2")
                .addPreferenceCatalog("testKey2", "content2")
                .addPreferenceScreen("s1", R.string.app_name)
                .addPreferenceScreen("s2", R.string.app_name)
                .addPreferenceList("11", "list title", R.array.sensitivity_names, R.array.sensitivity_values)
                .apply();


    }
}
