package com.example.notepad.database.repository;

import com.example.notepad.database.entity.Note;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface NoteRepository {

    Flowable<List<Note>> getAllNotes();

    Flowable<List<Note>> getAllNoteByCategory(String category);

    Flowable<List<Note>> getFavouriteNotes();

    Single<Note> getNoteById(int id);

    void insertNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);
}
