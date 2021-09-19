package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private ImageView ivReset;
    private ImageView ivMusic;
    private MediaPlayer music;
    public boolean bMusic;
    Music cMusic = new Music();
    private StoreItemDAO storeItemDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ivReset = findViewById(R.id.iv_reset);
        ivMusic = findViewById(R.id.iv_music);

        bMusic = cMusic.getBMusic();

        ivMusic.setOnClickListener(v ->{
            if(bMusic){
                bMusic = false;
                cMusic.setBMusic(bMusic);
                ivMusic.setImageResource(R.drawable.off);
            }
            else{
                bMusic = true;
                cMusic.setBMusic(bMusic);
                ivMusic.setImageResource(R.drawable.on);
            }
        });

        ivReset.setOnClickListener(v -> {
            storeItemDAO = new StoreItemDAOSQLImpl(getApplicationContext());

            storeItemDAO.resetStoreItem();

            StoreItemDataHelper dataHelper = new StoreItemDataHelper();
            dataHelper.initializeData(storeItemDAO);

            MainActivity.informationStorage.setCurrency(MainActivity.informationStorage.CURRENCY, 100000);

            Toast.makeText(getApplicationContext(), "Successfully reset!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        bMusic = cMusic.getBMusic();

        if (bMusic) {
            music = MediaPlayer.create(SettingsActivity.this, R.raw.music);
            music.start();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        music.stop();
        music.release();
    }
}