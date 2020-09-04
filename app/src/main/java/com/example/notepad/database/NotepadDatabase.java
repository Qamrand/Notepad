package com.example.notepad.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notepad.database.entity.Category;
import com.example.notepad.database.entity.CategoryDao;
import com.example.notepad.database.entity.Note;
import com.example.notepad.database.entity.NoteDao;

@Database(entities = {Category.class, Note.class}, version = NotepadDatabase.VERSION_DB, exportSchema = false)
public abstract class NotepadDatabase extends RoomDatabase {
    public static final int VERSION_DB = 1;

    public abstract CategoryDao getCategoryDao();

    public abstract NoteDao getNoteDao();
}
