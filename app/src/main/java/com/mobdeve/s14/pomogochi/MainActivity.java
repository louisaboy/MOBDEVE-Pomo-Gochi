package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.s14.pomogochi.util.InformationStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tv_money;

    private StoreItemDAO storeItemDAO;

    private ImageView
            iv_pet1,
            iv_pet2,
            iv_pet3,
            iv_pet4,
            iv_pet5,
            iv_pet6,
            iv_pet7,
            iv_pet8,
            iv_pet9,
            iv_pet10,
            iv_pet11,
            iv_pet12,
            iv_pet13;

    public static InformationStorage informationStorage;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Call setCatVisibility()

        // Datahelper init can put into initFunction
        informationStorage = new InformationStorage(this);

        if(informationStorage.getFirst(informationStorage.FIRST)) {
            this.storeItemDAO = new StoreItemDAOSQLImpl(this);
            StoreItemDataHelper dataHelper = new StoreItemDataHelper();
            dataHelper.initializeData(storeItemDAO);

            informationStorage.setFirst(informationStorage.FIRST, false);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigation();

        ImageView iv_timer = findViewById(R.id.iv_timer);
        ImageView iv_todo = findViewById(R.id.iv_todo);
        ImageView iv_shop = findViewById(R.id.ib_shop);
        ImageView iv_settings = findViewById(R.id.ib_settings);
        tv_money = findViewById(R.id.tv_money);


        // for drag and drop function
        iv_pet1 = findViewById(R.id.iv_pet1);
        iv_pet2 = findViewById(R.id.iv_pet2);
        iv_pet3 = findViewById(R.id.iv_pet3);
        iv_pet4 = findViewById(R.id.iv_pet4);
        iv_pet5 = findViewById(R.id.iv_pet5);
        iv_pet6 = findViewById(R.id.iv_pet6);
        iv_pet7 = findViewById(R.id.iv_pet7);
        iv_pet8 = findViewById(R.id.iv_pet8);
        iv_pet9 = findViewById(R.id.iv_pet9);
        iv_pet10 = findViewById(R.id.iv_pet10);
        iv_pet11 = findViewById(R.id.iv_pet11);
        iv_pet12 = findViewById(R.id.iv_pet12);
        iv_pet13 = findViewById(R.id.iv_pet13);

        setIvPetOnTouchListener(iv_pet1);
        setIvPetOnTouchListener(iv_pet2);
        setIvPetOnTouchListener(iv_pet3);
        setIvPetOnTouchListener(iv_pet4);
        setIvPetOnTouchListener(iv_pet5);
        setIvPetOnTouchListener(iv_pet6);
        setIvPetOnTouchListener(iv_pet7);
        setIvPetOnTouchListener(iv_pet8);
        setIvPetOnTouchListener(iv_pet9);
        setIvPetOnTouchListener(iv_pet1);
        setIvPetOnTouchListener(iv_pet1);
        setIvPetOnTouchListener(iv_pet1);
        setIvPetOnTouchListener(iv_pet1);


        tv_money.setText(String.valueOf(informationStorage.getCurrency(informationStorage.CURRENCY)));
        iv_timer.setOnClickListener(v -> toPomodoro());

        iv_todo.setOnClickListener(v -> toToDo());

        iv_shop.setOnClickListener(v -> toShop());

        iv_settings.setOnClickListener(v -> toSettings());
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_money.setText(String.valueOf(informationStorage.getCurrency(informationStorage.CURRENCY)));
        this.storeItemDAO = new StoreItemDAOSQLImpl(this);

        // TODO Call setCatVisibility()
        setCatVisibility();
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

    private void initNavigation() {
        final int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setIvPetOnTouchListener(ImageView iv) {
        iv.setOnTouchListener((v, event) -> {
            float xPos, yPos;
            xPos = event.getX();
            yPos = event.getY();

            switch (event.getActionMasked()) {
                // the user just put his finger down on the image view
                case MotionEvent.ACTION_DOWN:
                    xPos = event.getX();
                    yPos = event.getY();
                    break;

                // the user moved his finger
                case MotionEvent.ACTION_MOVE:
                    float movedX, movedY;
                    movedX = event.getX();
                    movedY = event.getY();

                    // calculate how much the user moved its fingers
                    float distanceX = movedX - xPos;
                    float distanceY = movedY - yPos;

                    // now move the view to that position
                    iv.setX(iv.getX() + distanceX);
                    iv.setY(iv.getY() + distanceY);

                    break;
            }
            return true;
        });
    }

    public void setCatVisibility() {
        ArrayList<StoreItemModel> dataStoreItem = storeItemDAO.getAllStoreItem();
        if(dataStoreItem.get(0).getOwned()) {
            iv_pet1.setVisibility(View.VISIBLE);
        } else {
            iv_pet1.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(1).getOwned()) {
            iv_pet2.setVisibility(View.VISIBLE);
        } else {
            iv_pet2.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(2).getOwned()) {
            iv_pet3.setVisibility(View.VISIBLE);
        } else {
            iv_pet3.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(3).getOwned()) {
            iv_pet4.setVisibility(View.VISIBLE);
        } else {
            iv_pet4.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(4).getOwned()) {
            iv_pet5.setVisibility(View.VISIBLE);
        } else {
            iv_pet5.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(5).getOwned()) {
            iv_pet6.setVisibility(View.VISIBLE);
        } else {
            iv_pet6.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(6).getOwned()) {
            iv_pet7.setVisibility(View.VISIBLE);
        } else {
            iv_pet7.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(7).getOwned()) {
            iv_pet8.setVisibility(View.VISIBLE);
        } else {
            iv_pet8.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(8).getOwned()) {
            iv_pet9.setVisibility(View.VISIBLE);
        } else {
            iv_pet9.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(9).getOwned()) {
            iv_pet10.setVisibility(View.VISIBLE);
        } else {
            iv_pet10.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(10).getOwned()) {
            iv_pet11.setVisibility(View.VISIBLE);
        } else {
            iv_pet11.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(11).getOwned()) {
            iv_pet12.setVisibility(View.VISIBLE);
        } else {
            iv_pet12.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(12).getOwned()) {
            iv_pet13.setVisibility(View.VISIBLE);
        } else {
            iv_pet13.setVisibility(View.GONE);
        }
    }
}