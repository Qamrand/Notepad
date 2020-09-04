package com.example.notepad.database.entity;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    Flowable<List<Note>> getAllNotes();

    @Query("SELECT * FROM note WHERE category = (:category)")
    Flowable<List<Note>> getAllNoteByCategory(String category);

    @Query("SELECT * FROM note WHERE favourite = 1")
    Flowable<List<Note>> getFavouriteNotes();

    /**
    * Передача по ИД через интент гарантирует, что данная NOTE существует.
    * */
    @Query("SELECT * FROM note WHERE id = (:id)")
    Single<Note> getNoteById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
