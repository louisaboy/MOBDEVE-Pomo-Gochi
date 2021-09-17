package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private ImageView ivReset;

    private StoreItemDAO storeItemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ivReset = findViewById(R.id.iv_reset);

        ivReset.setOnClickListener(v -> {
            storeItemDAO = new StoreItemDAOSQLImpl(getApplicationContext());

            storeItemDAO.resetStoreItem();

            StoreItemDataHelper dataHelper = new StoreItemDataHelper();
            dataHelper.initializeData(storeItemDAO);

            MainActivity.informationStorage.setCurrency(MainActivity.informationStorage.CURRENCY, 100000);

            Toast.makeText(getApplicationContext(), "Successfully reset!", Toast.LENGTH_SHORT).show();
        });
    }
}