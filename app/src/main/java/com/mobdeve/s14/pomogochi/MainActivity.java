package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobdeve.s14.pomogochi.util.InformationStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvMoney;
    private MediaPlayer music;
    public String bMusic = "true";

    private StoreItemDAO storeItemDAO;

    private ImageView
            ivPet1,
            ivPet2,
            ivPet3,
            ivPet4,
            ivPet5,
            ivPet6,
            ivPet7,
            ivPet8,
            ivPet9,
            ivPet10,
            ivPet11,
            ivPet12,
            ivPet13;

    private float xDown, yDown;

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

        ImageView ivTimer = findViewById(R.id.iv_timer);
        ImageView ivTodo = findViewById(R.id.iv_todo);
        ImageView ivShop = findViewById(R.id.ib_shop);
        ImageView ivSettings = findViewById(R.id.ib_settings);
        tvMoney = findViewById(R.id.tv_money);


        // getting the id of each pet image views
        ivPet1 = findViewById(R.id.iv_pet1);
        ivPet2 = findViewById(R.id.iv_pet2);
        ivPet3 = findViewById(R.id.iv_pet3);
        ivPet4 = findViewById(R.id.iv_pet4);
        ivPet5 = findViewById(R.id.iv_pet5);
        ivPet6 = findViewById(R.id.iv_pet6);
        ivPet7 = findViewById(R.id.iv_pet7);
        ivPet8 = findViewById(R.id.iv_pet8);
        ivPet9 = findViewById(R.id.iv_pet9);
        ivPet10 = findViewById(R.id.iv_pet10);
        ivPet11 = findViewById(R.id.iv_pet11);
        ivPet12 = findViewById(R.id.iv_pet12);
        ivPet13 = findViewById(R.id.iv_pet13);

        // drag functions for the image views
        setIvPetOnTouchListener(ivPet1);
        setIvPetOnTouchListener(ivPet2);
        setIvPetOnTouchListener(ivPet3);
        setIvPetOnTouchListener(ivPet4);
        setIvPetOnTouchListener(ivPet5);
        setIvPetOnTouchListener(ivPet6);
        setIvPetOnTouchListener(ivPet7);
        setIvPetOnTouchListener(ivPet8);
        setIvPetOnTouchListener(ivPet9);
        setIvPetOnTouchListener(ivPet10);
        setIvPetOnTouchListener(ivPet11);
        setIvPetOnTouchListener(ivPet12);
        setIvPetOnTouchListener(ivPet13);

        // sets the currency based on what was stored in the shared preference
        tvMoney.setText(String.valueOf(informationStorage.getCurrency(informationStorage.CURRENCY)));


        // set on click listener for the icons
        ivTimer.setOnClickListener(v -> toPomodoro());
        ivTodo.setOnClickListener(v -> toToDo());
        ivShop.setOnClickListener(v -> toShop());
        ivSettings.setOnClickListener(v -> toSettings());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (bMusic.equals("true")) {
            music = MediaPlayer.create(MainActivity.this, R.raw.music);
            music.start();
        }
        tvMoney.setText(String.valueOf(informationStorage.getCurrency(informationStorage.CURRENCY)));
        this.storeItemDAO = new StoreItemDAOSQLImpl(this);

        // TODO Call setCatVisibility()
        setCatVisibility();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (bMusic.equals("true")) {
            music.stop();
            music.release();
        }
    }

    // intent to pomodoro
    private void toPomodoro() {
        Intent toPomodoroIntent = new Intent(this, PomodoroTimer.class);
        toPomodoroIntent.putExtra("Music", bMusic);
        startActivity(toPomodoroIntent);
    }

    // intent to todo
    private void toToDo() {
        Intent toToDo = new Intent(this, TodoListActivity.class);
        toToDo.putExtra("Music", bMusic);
        startActivity(toToDo);
    }

    // intent to shop
    private void toShop() {
        Intent toShop = new Intent(this, StoreItemActivity.class);
        toShop.putExtra("Music", bMusic);
        startActivity(toShop);
    }

    // intent to Settings
    private void toSettings() {
        Intent toSettings = new Intent(this, SettingsActivity.class);
        toSettings.putExtra("Music", bMusic);
        startActivityForResult(toSettings, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                String returnString = data.getStringExtra("Music");
                bMusic = returnString;
            }
        }
    }

    // initialize navigation
    private void initNavigation() {
        final int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(uiOptions);
    }

    // function for dragging the image views
    @SuppressLint("ClickableViewAccessibility")
    private void setIvPetOnTouchListener(ImageView iv) {
        iv.setOnTouchListener((v, event) -> {
            switch (event.getActionMasked()) {
                // the user just put his finger down on the image view
                case MotionEvent.ACTION_DOWN:
                    xDown = event.getX();
                    yDown = event.getY();
                    break;

                // the user moved his finger
                case MotionEvent.ACTION_MOVE:
                    float movedX, movedY;
                    movedX = event.getX();
                    movedY = event.getY();

                    // calculate how much the user moved its fingers
                    float distanceX = movedX - xDown;
                    float distanceY = movedY - yDown;

                    // now move the view to that position
                    iv.setX(iv.getX() + distanceX);
                    iv.setY(iv.getY() + distanceY);

                    break;
            }
            return true;
        });
    }

    // sets the cat's visibility depending on the status of the pet whether owned or not
    public void setCatVisibility() {
        ArrayList<StoreItemModel> dataStoreItem = storeItemDAO.getAllStoreItem();
        if(dataStoreItem.get(0).getOwned()) {
            ivPet1.setVisibility(View.VISIBLE);
        } else {
            ivPet1.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(1).getOwned()) {
            ivPet2.setVisibility(View.VISIBLE);
        } else {
            ivPet2.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(2).getOwned()) {
            ivPet3.setVisibility(View.VISIBLE);
        } else {
            ivPet3.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(3).getOwned()) {
            ivPet4.setVisibility(View.VISIBLE);
        } else {
            ivPet4.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(4).getOwned()) {
            ivPet5.setVisibility(View.VISIBLE);
        } else {
            ivPet5.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(5).getOwned()) {
            ivPet6.setVisibility(View.VISIBLE);
        } else {
            ivPet6.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(6).getOwned()) {
            ivPet7.setVisibility(View.VISIBLE);
        } else {
            ivPet7.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(7).getOwned()) {
            ivPet8.setVisibility(View.VISIBLE);
        } else {
            ivPet8.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(8).getOwned()) {
            ivPet9.setVisibility(View.VISIBLE);
        } else {
            ivPet9.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(9).getOwned()) {
            ivPet10.setVisibility(View.VISIBLE);
        } else {
            ivPet10.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(10).getOwned()) {
            ivPet11.setVisibility(View.VISIBLE);
        } else {
            ivPet11.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(11).getOwned()) {
            ivPet12.setVisibility(View.VISIBLE);
        } else {
            ivPet12.setVisibility(View.GONE);
        }
        if(dataStoreItem.get(12).getOwned()) {
            ivPet13.setVisibility(View.VISIBLE);
        } else {
            ivPet13.setVisibility(View.GONE);
        }
    }
}