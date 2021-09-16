package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SettingsActivity extends AppCompatActivity {
    private ImageView iv_timer;
    private ImageView iv_todo;
    private ImageView iv_shop;
    private ImageView iv_settings;
    private ImageView iv_home;
    private ImageView iv_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        iv_timer = findViewById(R.id.iv_timer);
        iv_todo = findViewById(R.id.iv_todo);
        iv_shop = findViewById(R.id.ib_shop);
        iv_settings = findViewById(R.id.ib_settings);
        iv_home = findViewById(R.id.iv_home);
        iv_reset = findViewById(R.id.iv_reset);


        iv_reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}