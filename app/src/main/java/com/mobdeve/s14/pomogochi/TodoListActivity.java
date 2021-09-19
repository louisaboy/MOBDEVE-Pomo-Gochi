package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TodoListActivity extends AppCompatActivity
{
    private ListView noteListView;
    private TextView tvMoney;
    private MediaPlayer music;
    public boolean bMusic;
    Music cMusic = new Music();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();
    }


    private void initWidgets()
    {
        noteListView = findViewById(R.id.rvToDoList);
        tvMoney = findViewById(R.id.tv_money);

        tvMoney.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(TodoListActivity.this);
        sqLiteManager.populateNoteListArray();
    }

    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }


    private void setOnClickListener() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Note selectedNote = (Note) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), NoteDetailActivity.class);
                editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA, selectedNote.getId());
                startActivity(editNoteIntent);
            }
        });
    }

    public void newNote(View view)
    {
        Intent newNoteIntent = new Intent(TodoListActivity.this, NoteDetailActivity.class);
        startActivity(newNoteIntent);
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        tvMoney.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));

        bMusic = cMusic.getBMusic();

        if (bMusic) {
            music = MediaPlayer.create(TodoListActivity.this, R.raw.music);
            music.start();
        }

        Note.deleteAll();
        loadFromDBToMemory();
        setNoteAdapter();
    }

    @Override
    protected void onPause(){
        super.onPause();
        music.stop();
        music.release();
    }
}