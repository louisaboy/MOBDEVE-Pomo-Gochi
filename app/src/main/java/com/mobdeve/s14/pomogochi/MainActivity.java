package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.s14.pomogochi.util.InformationStorage;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_timer;
    private ImageView iv_todo;
    private ImageView iv_shop;
    private ImageView iv_settings;
    private ImageView iv_home;
    private TextView tv_money;

    private StoreItemDAO storeItemDAO;

    public static InformationStorage informationStorage;

//    private ImageView iv_cat_1, iv_cat_2, iv_cat_3, iv_cat_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Datahelper init can put into initFunction
        informationStorage = new InformationStorage(this);

        if(informationStorage.getFirst(informationStorage.FIRST)) {
            this.storeItemDAO = new StoreItemDAOSQLImpl(this);
            StoreItemDataHelper dataHelper = new StoreItemDataHelper();
            dataHelper.initializeData(this.storeItemDAO);

            informationStorage.setFirst(informationStorage.FIRST, false);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigation();

        iv_timer = findViewById(R.id.iv_timer);
        iv_todo = findViewById(R.id.iv_todo);
        iv_shop = findViewById(R.id.ib_shop);
        iv_settings = findViewById(R.id.ib_settings);
        iv_home = findViewById(R.id.iv_home);
        tv_money = findViewById(R.id.tv_money);

        tv_money.setText(String.valueOf(informationStorage.getCurrency(informationStorage.CURRENCY)));
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

    @Override
    protected void onResume() {
        super.onResume();
        tv_money.setText(String.valueOf(informationStorage.getCurrency(informationStorage.CURRENCY)));
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
        Intent toPomodoroIntent = new Intent(this, StoreItemActivity.class);
        startActivity(toPomodoroIntent);
    }

    private void toSettings() {
        Intent toPomodoroIntent = new Intent(this, SettingsActivity.class);
        startActivity(toPomodoroIntent);
    }

    private void initNavigation(){
        final int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
    }

}