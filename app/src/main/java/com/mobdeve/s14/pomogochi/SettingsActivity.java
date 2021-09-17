package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private ImageView iv_reset;

    private StoreItemDAO storeItemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        iv_reset = findViewById(R.id.iv_reset);

        iv_reset.setOnClickListener(v -> {
            storeItemDAO = new StoreItemDAOSQLImpl(getApplicationContext());

            storeItemDAO.resetStoreItem();

            StoreItemDataHelper dataHelper = new StoreItemDataHelper();
            dataHelper.initializeData(storeItemDAO);

            MainActivity.informationStorage.setCurrency(MainActivity.informationStorage.CURRENCY, 100000);

            Toast.makeText(getApplicationContext(), "Successfully reset!", Toast.LENGTH_SHORT).show();
        });
    }
}