package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreItemActivity extends AppCompatActivity {

    StoreItemDAO storeItemDAO;

    private ImageView iv_timer;
    private ImageView iv_todo;
    private ImageView iv_shop;
    private ImageView iv_settings;
    private ImageView iv_home;
    public static TextView tv_money;
    private MediaPlayer music;
    public String bMusic;

    private RecyclerView rvStoreItems;

    private RecyclerView.LayoutManager rvLayoutManager;

    private StoreItemAdapter storeItemAdapter;

    private ArrayList<StoreItemModel> dataStoreItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        this.storeItemDAO = new StoreItemDAOSQLImpl(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_items);
        Intent intent = getIntent();
        bMusic = intent.getStringExtra("Music");

        this.initDataStoreItems();
        this.initRecyclerView();

        iv_timer = findViewById(R.id.iv_timer);
        iv_todo = findViewById(R.id.iv_todo);
        iv_shop = findViewById(R.id.ib_shop);
        iv_settings = findViewById(R.id.ib_settings);
        iv_home = findViewById(R.id.iv_home);

        tv_money = findViewById(R.id.tv_money);

        tv_money.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));
    }

    private void initDataStoreItems() {
        this.dataStoreItems = this.storeItemDAO.getAllStoreItem();
    }

    private void initRecyclerView() {
        this.rvStoreItems = findViewById(R.id.rv_store_items);

        this.rvLayoutManager = new GridLayoutManager(this, 3);
        this.rvStoreItems.setLayoutManager(rvLayoutManager);

        this.storeItemAdapter = new StoreItemAdapter(this.dataStoreItems, this.storeItemDAO);
        this.rvStoreItems.setAdapter(this.storeItemAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();

        if (bMusic.equals("true")) {
            music = MediaPlayer.create(StoreItemActivity.this, R.raw.music);
            music.start();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (bMusic.equals("true")) {
            music.stop();
            music.release();
        }
    }
}
