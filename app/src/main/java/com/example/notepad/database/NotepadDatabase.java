package com.example.notepad.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notepad.database.entity.Category;
import com.example.notepad.database.entity.CategoryDao;
import com.example.notepad.database.entity.Note;
import com.example.notepad.database.entity.NoteDao;

@Database(entities = {Category.class, Note.class}, version = NotepadDatabase.VERSION_DB_2, exportSchema = false)
public abstract class NotepadDatabase extends RoomDatabase {

    public static final String DB_NAME = "notepad_db";

    public static final int VERSION_DB_1 = 1;
    public static final int VERSION_DB_2 = 2;


    public static final Migration MIGRATION_1_2 = new Migration(VERSION_DB_1, VERSION_DB_2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE category");
            String sb = "CREATE TABLE category (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "name TEXT)";
            database.execSQL(sb);
        }
    };

    public abstract CategoryDao getCategoryDao();

    public abstract NoteDao getNoteDao();

}
