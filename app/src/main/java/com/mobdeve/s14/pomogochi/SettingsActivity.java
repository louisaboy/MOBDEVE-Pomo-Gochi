package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private ImageView ivReset;
    private ImageView ivMusic;
    private MediaPlayer music;
    private ImageView ivExit;
    public String bMusic;
    private StoreItemDAO storeItemDAO;
    private int r;


    // Creates an instance of the Settings Activity. Also, this function loads all the widgets inside the settings.
    // It also listens to the clicks of the user wherein the user can reset his/her progress, turn the music on/ off
    // and exit the settings
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        bMusic = intent.getStringExtra("Music");

        ivReset = findViewById(R.id.iv_reset);
        ivMusic = findViewById(R.id.iv_music);
        ivExit = findViewById(R.id.iv_close);

        if (bMusic.equals("true"))
            ivMusic.setImageResource(R.drawable.on);
        else
            ivMusic.setImageResource(R.drawable.off);

        ivMusic.setOnClickListener(v ->{
            if(bMusic.equals("true")){
                bMusic = "false";
                ivMusic.setImageResource(R.drawable.off);
                Intent i = new Intent();
                i.putExtra("Music", bMusic);
                setResult(RESULT_OK, i);
            }
            else{
                bMusic = "true";
                ivMusic.setImageResource(R.drawable.on);
                Intent i = new Intent();
                i.putExtra("Music", bMusic);
                setResult(RESULT_OK, i);
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

        ivExit.setOnClickListener(v ->{
            finish();
        });
    }

    // creates a Resume instance wherein the music plays if the music settings is on
    @Override
    protected void onResume(){
        super.onResume();

        r = 0;

        if (bMusic.equals("true")) {
            music = MediaPlayer.create(SettingsActivity.this, R.raw.music);
            music.start();
            r = 1;
        }
    }

    // Turns the music off
    @Override
    protected void onPause(){
        super.onPause();
        if ( r == 1) {
            music.stop();
            music.release();
        }
    }
}