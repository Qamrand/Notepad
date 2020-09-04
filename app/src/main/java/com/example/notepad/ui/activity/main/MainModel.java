package com.example.notepad.ui.activity.main;

import com.example.notepad.MyApplication;

import io.reactivex.Flowable;

public class MainModel implements MainContract.Model{

    @Override
    public Flowable showAllNotes() {
        return MyApplication.noteRepository.getAllNotes();
    }
}
