package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

//    private ViewGroup rootLayout1;
    private ImageView iv_pet1;


    private float xDown, yDown;

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


        // for drag and drop function
        iv_pet1 = (ImageView) findViewById(R.id.iv_pet1);
//        rootLayout1 = (ViewGroup) findViewById(R.id.view_root);

//        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(150,150);
//        iv_pet1.setLayoutParams(layoutParams);
//        iv_pet1.setOnTouchListener(new ChoiceTouchListener());

        iv_pet1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ;
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
                        iv_pet1.setX(iv_pet1.getX() + distanceX);
                        iv_pet1.setY(iv_pet1.getY() + distanceY);

                        break;
                }

                return true;
            }
        });


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

//    private final class ChoiceTouchListener implements View.OnTouchListener {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            final int X = (int) event.getRawX();
//            final int Y = (int) event.getRawY();
//            switch (event.getAction() & MotionEvent.ACTION_MASK){
//                case MotionEvent.ACTION_DOWN:
//                    ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
//                    x1 = X - lParams.leftMargin;
//                    y1 = Y - lParams.topMargin;
//                    break;
//
//                case MotionEvent.ACTION_UP:
//                    break;
//
//                case MotionEvent.ACTION_POINTER_DOWN:
//                    break;
//
//                case MotionEvent.ACTION_POINTER_UP:
//                    break;
//
//                case MotionEvent.ACTION_MOVE:
//                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
//
//                    layoutParams.leftMargin = X - x1;
//                    layoutParams.topMargin = Y - y1;
//                    layoutParams.rightMargin = -250;
//                    layoutParams.bottomMargin = -250;
//                    v.setLayoutParams(layoutParams);
//                    break;
//             }
//             rootLayout1.invalidate();
//
//            return true;
//        }
//    }
}