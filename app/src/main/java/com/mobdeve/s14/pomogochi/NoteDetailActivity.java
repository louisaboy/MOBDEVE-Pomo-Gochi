package com.mobdeve.s14.pomogochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
<<<<<<< HEAD
        titleEditText = findViewById(R.id.et_title);
        descEditText = findViewById(R.id.et_desc);
        deleteButton = findViewById(R.id.b_delete);
        doneButton = findViewById(R.id.b_done);
        tv_money = findViewById(R.id.tv_money);
=======
        titleEditText = findViewById(R.id.etTitle);
        descEditText = findViewById(R.id.etDesc);
        deleteButton = findViewById(R.id.bDelete);
        doneButton = findViewById(R.id.bDone);
        tvMoney = findViewById(R.id.tv_money);
>>>>>>> e6ef75bd9ff00df4236097f2d258c2121058a0b4

        tvMoney.setText(String.valueOf(MainActivity.informationStorage.getCurrency(MainActivity.informationStorage.CURRENCY)));
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