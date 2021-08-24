package com.mobdeve.s11.sibug.jordan.exercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class StoreItemsActivity extends AppCompatActivity {

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
    }

    private void initDataStoreItems() {
        StoreItemsDataHelper dataHelper = new StoreItemsDataHelper();
        this.dataStoreItems = dataHelper.initializeData();
    }

    private void initRecyclerView() {
        this.rvStoreItems = findViewById(R.id.rv_store_items);

        this.rvLayoutManager = new GridLayoutManager(this, 3);
        this.rvStoreItems.setLayoutManager(rvLayoutManager);

        this.storeItemAdapter = new StoreItemAdapter(dataStoreItems);
        this.rvStoreItems.setAdapter(this.storeItemAdapter);
    }
}
