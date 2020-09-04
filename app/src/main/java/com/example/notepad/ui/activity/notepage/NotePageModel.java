package com.example.notepad.ui.activity.notepage;

import com.example.notepad.MyApplication;
import com.example.notepad.database.entity.Note;

import io.reactivex.Single;

public class NotePageModel implements NotePageContract.Model {

    public NotePageModel() {
    }

    @Override
    public Single<Note> getNoteById(int id) {
        return MyApplication.noteRepository.getNoteById(id);
    }

    @Override
    public void updateNote(Note note) {
        MyApplication.noteRepository.updateNote(note);
    }

    @Override
    public void deleteNote(Note note) {
        MyApplication.noteRepository.deleteNote(note);
    }
}
