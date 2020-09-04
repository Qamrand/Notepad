package com.example.notepad.database.repository;

import androidx.lifecycle.LiveData;

import com.example.notepad.database.entity.Note;
import com.example.notepad.database.entity.NoteDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class NoteDataSource implements NoteRepository {

    private NoteDao mNoteDao;

    @Inject
    public NoteDataSource(NoteDao noteDao) {
        mNoteDao = noteDao;
    }

    @Override
    public Flowable<List<Note>> getAllNotes() {
        return mNoteDao.getAllNotes();
    }

    @Override
    public Flowable<List<Note>> getAllNoteByCategory(String category) {
        return mNoteDao.getAllNoteByCategory(category);
    }

    @Override
    public Single<Note> getNoteById(int id) {
        return mNoteDao.getNoteById(id);
    }

    @Override
    public void insertNote(Note note) {
        mNoteDao.insertNote(note);
    }

    @Override
    public void updateNote(Note note) {
        mNoteDao.updateNote(note);
    }

    @Override
    public void deleteNote(Note note) {
        mNoteDao.deleteNote(note);
    }
}
