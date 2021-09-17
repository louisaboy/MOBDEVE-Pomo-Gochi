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
    private TextView tv_money;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        initWidgets();
        checkForEditNote();
    }

    private void initWidgets()
    {
        titleEditText = findViewById(R.id.etTitle);
        descEditText = findViewById(R.id.etDesc);
        deleteButton = findViewById(R.id.bDelete);
        doneButton = findViewById(R.id.bDone);
        tv_money = findViewById(R.id.tv_money);

        tv_money.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));
    }

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

    public void deleteNote(View view)
    {
        selectedNote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }


    // allen dito ka mag add ng logic for money
    public void doneNote(View view)
    {
        selectedNote.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedNote);

        int money = 1000;

        tv_money.setText(String.valueOf(Integer.parseInt(tv_money.getText().toString()) + money));
        int total_money = MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY) + money;
        tv_money.setText(String.valueOf(total_money));
        MainActivity.informationStorage.setCurrency(MainActivity.informationStorage.CURRENCY, total_money);

        finish();
    }
}