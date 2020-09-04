package com.example.notepad.ui.activity.addnote;

import com.example.notepad.MyApplication;
import com.example.notepad.database.entity.Note;

public class AddNoteModel implements AddNoteContract.Model{
    @Override
    public void addNote(Note note) {
        MyApplication.noteRepository.insertNote(note);
    }
}
