package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_timer;
    private ImageView iv_todo;
    private ImageView iv_shop;
    private ImageView iv_settings;
    private ImageView iv_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_timer = findViewById(R.id.iv_timer);
        iv_todo = findViewById(R.id.iv_todo);
        iv_shop = findViewById(R.id.ib_shop);
        iv_settings = findViewById(R.id.ib_settings);
        iv_home = findViewById(R.id.iv_home);

        iv_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPomodoro();
            }
        });

        iv_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toToDo();
            }
        });

        iv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toShop();
            }
        });

        iv_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSettings();
            }
        });
    }

    private void toPomodoro() {
        Intent toPomodoroIntent = new Intent(this, PomodoroTimer.class);
        startActivity(toPomodoroIntent);
    }

    private void toToDo() {
        Intent toPomodoroIntent = new Intent(this, TodoListActivity.class);
        startActivity(toPomodoroIntent);
    }

    private void toShop() {
        Intent toPomodoroIntent = new Intent(this, StoreItemsActivity.class);
        startActivity(toPomodoroIntent);
    }

    private void toSettings() {
        Intent toPomodoroIntent = new Intent(this, SettingsActivity.class);
        startActivity(toPomodoroIntent);
    }


}