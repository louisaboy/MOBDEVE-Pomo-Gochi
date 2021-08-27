package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class StoreItemsActivity extends AppCompatActivity {
    private ImageView iv_timer;
    private ImageView iv_todo;
    private ImageView iv_shop;
    private ImageView iv_settings;
    private ImageView iv_home;

    private ImageView iv_buy;
    private ImageView iv_owned;
    private boolean owned = false;

    private RecyclerView rvStoreItems;

    private RecyclerView.LayoutManager rvLayoutManager;

    private StoreItemAdapter storeItemAdapter;

    private ArrayList<StoreItem> dataStoreItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_items);

        this.initDataStoreItems();
        this.initRecyclerView();

        iv_timer = findViewById(R.id.iv_timer);
        iv_todo = findViewById(R.id.iv_todo);
        iv_shop = findViewById(R.id.ib_shop);
        iv_settings = findViewById(R.id.ib_settings);
        iv_home = findViewById(R.id.iv_home);
        iv_buy = findViewById(R.id.iv_buy);
        iv_owned = findViewById(R.id.iv_owned);

//        iv_buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                owned = true;
//                iv_owned.setVisibility(View.VISIBLE);
//                iv_buy.setVisibility(View.INVISIBLE);
//            }
//        });
////
//////        iv_owned.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                owned = false;
//////                iv_owned.setVisibility(View.INVISIBLE);
//////                iv_buy.setVisibility(View.VISIBLE);
//////            }
//////        });

    }

    private void initDataStoreItems() {
        StoreItemsDataHelper dataHelper = new StoreItemsDataHelper();
        this.dataStoreItems = dataHelper.initializeData();
    }

    private void initRecyclerView() {
        this.rvStoreItems = findViewById(R.id.rv_store_items);

        this.rvLayoutManager = new GridLayoutManager(this, 3);
        this.rvStoreItems.setLayoutManager(rvLayoutManager);

        this.storeItemAdapter = new StoreItemAdapter(this.dataStoreItems);
        this.rvStoreItems.setAdapter(this.storeItemAdapter);
    }


}
