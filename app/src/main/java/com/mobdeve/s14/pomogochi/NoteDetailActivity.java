package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity
{
    private EditText titleEditText, descEditText;
    private Button deleteButton, doneButton;
    private Note selectedNote;
    private TextView tvMoney;
    private MediaPlayer music;
    public String bMusic;


    // Creates an instance of NoteDetailActivity where it shows the entry that was clicked by the user.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        initWidgets();
        checkForEditNote();
    }

    // Loads the widgets that were used in the activty layout
    private void initWidgets()
    {
        titleEditText = findViewById(R.id.et_title);
        descEditText = findViewById(R.id.et_desc);
        deleteButton = findViewById(R.id.b_delete);
        doneButton = findViewById(R.id.b_done);
        tvMoney = findViewById(R.id.tv_money);


        tvMoney.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));
    }


    // this function checks if the user will either edit or add a new note in the list
    // if the selectedNote is null the user is only allowed to add a new list
    private void checkForEditNote()
    {
        Intent previousIntent = getIntent();

        int passedNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1);
        selectedNote = Note.getNoteForID(passedNoteID);

        if (selectedNote != null)
        {
            titleEditText.setText(selectedNote.getTitle());
            descEditText.setText(selectedNote.getDescription());
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
            doneButton.setVisibility(View.INVISIBLE);
        }
    }

    // this function saves the inputs of the user to the database
    public void saveNote(View view)
    {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedNote == null)
        {
            int id = Note.noteArrayList.size();
            Note newNote = new Note(id, title, desc);
            Note.noteArrayList.add(newNote);
            sqLiteManager.addNoteToDatabase(newNote);
        }
        else
        {
            selectedNote.setTitle(title);
            selectedNote.setDescription(desc);
            sqLiteManager.updateNoteInDB(selectedNote);
        }

        finish();
    }

    // this function delete the To Do entry of the user
    public void deleteNote(View view)
    {
        selectedNote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }

    // this function also deletes the entry in the To Do List but also credits him/her money for the task that he/she did
    public void doneNote(View view)
    {
        selectedNote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);

        int money = 1000;

        tvMoney.setText(String.valueOf(Integer.parseInt(tvMoney.getText().toString()) + money));
        int total_money = MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY) + money;
        tvMoney.setText(String.valueOf(total_money));
        MainActivity.informationStorage.setCurrency(MainActivity.informationStorage.CURRENCY, total_money);

        finish();
    }
}