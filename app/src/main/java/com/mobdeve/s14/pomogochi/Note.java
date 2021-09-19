package com.mobdeve.s14.pomogochi;

import java.util.ArrayList;
import java.util.Date;

public class Note
{
    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA =  "noteEdit";

    private int id;
    private String title;
    private String description;
    private Date deleted;

    public Note(int id, String title, String description, Date deleted)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public Note(int id, String title, String description)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    // deletes all the notes in the arraylist
    public static void deleteAll ()
    {
        noteArrayList.clear();
    }


    // get the Id of the note that was clicked by the user
    public static Note getNoteForID(int passedNoteID)
    {
        for (Note note : noteArrayList)
        {
            if(note.getId() == passedNoteID)
                return note;
        }

        return null;
    }


    // Gets the non deleted notes inside the list and puts it in the arraylist
    public static ArrayList<Note> nonDeletedNotes()
    {
        ArrayList<Note> nonDeleted = new ArrayList<>();
        for(Note note : noteArrayList)
        {
            if(note.getDeleted() == null)
                nonDeleted.add(note);
        }

        return nonDeleted;
    }

    // returns the Id of the note
    public int getId()
    {
        return id;
    }

    // sets the Id of the note
    public void setId(int id)
    {
        this.id = id;
    }

    // returns the title of the note
    public String getTitle()
    {
        return title;
    }

    // sets the title of the note
    public void setTitle(String title)
    {
        this.title = title;
    }

    // returns the description of the note
    public String getDescription()
    {
        return description;
    }

    // sets the descriptions of the note
    public void setDescription(String description)
    {
        this.description = description;
    }

    // checks if the entry in the list is already deleted
    public Date getDeleted()
    {
        return deleted;
    }

    // sets the entry to deleted if the user deleted the list in the database
    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }
}
