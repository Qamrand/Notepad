package com.example.notepad.ui.activity.favourite;

import com.example.notepad.MyApplication;
import com.example.notepad.database.entity.Note;

import java.util.List;

import io.reactivex.Flowable;

public class FavouriteModel implements FavouriteContract.Model {
    @Override
    public Flowable<List<Note>> getFavouriteNotes() {
        return MyApplication.noteRepository.getFavouriteNotes();
    }
}
