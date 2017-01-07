package com.ksxkq.materialpreference.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * OnePiece
 * Created by xukq on 1/7/17.
 */

public class ActivitySetting extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void click(View view) {
        Intent data = new Intent();
        data.putExtra("summary", "富坚义博");
        data.putExtra("key", getIntent().getStringExtra("key"));
        setResult(RESULT_OK, data);
        finish();
    }
}
