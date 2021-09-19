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
    public String bMusic;

    //creates an instance of the To Do List Activity
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        Intent intent = getIntent();
        bMusic = intent.getStringExtra("Music");
        initWidgets();
        loadFromDBToMemory();
        setNoteAdapter();
        setOnClickListener();
    }


    // loads the widgets inside the activity_todo_list.xml
    private void initWidgets()
    {
        noteListView = findViewById(R.id.rvToDoList);
        tvMoney = findViewById(R.id.tv_money);
        tvMoney.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));
    }

    // this function loads the local database that will be used on the database
    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(TodoListActivity.this);
        sqLiteManager.populateNoteListArray();
    }

    // this connects the activity to the adapter of the To Do List
    private void setNoteAdapter() {
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(), Note.nonDeletedNotes());
        noteListView.setAdapter(noteAdapter);
    }

    // sets an onClickListener to the item in the To Do List. If the user click the certain entry it goes to the NoteDetailActivity of that
    // item and the user can either edit the texts, delete or click done.
    private void setOnClickListener() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Note selectedNote = (Note) noteListView.getItemAtPosition(position);
                Intent editNoteIntent = new Intent(getApplicationContext(), NoteDetailActivity.class);
                editNoteIntent.putExtra(Note.NOTE_EDIT_EXTRA, selectedNote.getId());
                editNoteIntent.putExtra("Music", bMusic);
                startActivity(editNoteIntent);
            }
        });
    }

    // Creates a new note that will be placed on the database and To Do List.
    public void newNote(View view)
    {
        Intent newNoteIntent = new Intent(TodoListActivity.this, NoteDetailActivity.class);
        startActivity(newNoteIntent);
    }

    // this function loads the music that will be played in this activity and deletes the arraylist so that it clears the entries and
    // load the database that will be added in the arraylist again
    @Override
    protected void onResume()
    {
        super.onResume();
        tvMoney.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));

        if (bMusic.equals("true")) {
            music = MediaPlayer.create(TodoListActivity.this, R.raw.music);
            music.start();
        }

        Note.deleteAll();
        loadFromDBToMemory();
        setNoteAdapter();
    }

    // creates an onPause instance where the music will be stopped
    @Override
    protected void onPause(){
        super.onPause();
        if (bMusic.equals("true")) {
            music.stop();
            music.release();
        }
    }
}