package com.example.notepad.ui.activity.main;

import com.example.notepad.MyApplication;
import com.example.notepad.database.entity.Note;

import java.util.List;

import io.reactivex.Flowable;

public class MainModel implements MainContract.Model{

    public Flowable<List<Note>> getAllNotes() {
        return MyApplication.noteRepository.getAllNotes();
    }

    @Override
    public Flowable<List<Note>> getAllNotesByCategory(String category) {
        return MyApplication.noteRepository.getAllNoteByCategory(category);
    }
}
