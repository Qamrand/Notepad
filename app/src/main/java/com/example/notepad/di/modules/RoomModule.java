package com.example.notepad.di.modules;

import android.app.Application;

import androidx.room.Room;

import com.example.notepad.database.NotepadDatabase;
import com.example.notepad.database.entity.CategoryDao;
import com.example.notepad.database.entity.NoteDao;
import com.example.notepad.database.repository.CategoryDataSource;
import com.example.notepad.database.repository.CategoryRepository;
import com.example.notepad.database.repository.NoteDataSource;
import com.example.notepad.database.repository.NoteRepository;
import com.example.notepad.di.scopes.ApplicationScoped;

import dagger.Module;
import dagger.Provides;


@Module
public class RoomModule {

    private NotepadDatabase mNotepadDatabase;

    public RoomModule(Application application) {
        mNotepadDatabase =
                Room.databaseBuilder(application, NotepadDatabase.class, NotepadDatabase.DB_NAME)
                        .addMigrations(NotepadDatabase.MIGRATION_1_2)
                        .build();
    }

    //provide database object
    @Provides
    @ApplicationScoped
    NotepadDatabase providesNotepadDatabase() {
        return mNotepadDatabase;
    }

    //provides daos
    @Provides
    @ApplicationScoped
    NoteDao providesNoteDao(NotepadDatabase notepadDatabase) {
        return notepadDatabase.getNoteDao();
    }


    @Provides
    @ApplicationScoped
    CategoryDao providesCategoryDao(NotepadDatabase notepadDatabase) {
        return notepadDatabase.getCategoryDao();
    }

    //provides repositories
    @Provides
    @ApplicationScoped
    NoteRepository noteRepository(NoteDao noteDao) {
        return new NoteDataSource(noteDao);
    }

    @Provides
    @ApplicationScoped
    CategoryRepository categoryRepository(CategoryDao categoryDao) {
        return new CategoryDataSource(categoryDao);
    }

}
