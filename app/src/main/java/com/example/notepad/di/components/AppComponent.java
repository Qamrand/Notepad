package com.example.notepad.di.components;

import com.example.notepad.database.NotepadDatabase;
import com.example.notepad.database.entity.CategoryDao;
import com.example.notepad.database.entity.NoteDao;
import com.example.notepad.database.repository.CategoryRepository;
import com.example.notepad.database.repository.NoteRepository;
import com.example.notepad.di.modules.AppModule;
import com.example.notepad.di.modules.RoomModule;
import com.example.notepad.di.scopes.ApplicationScoped;
import com.example.notepad.ui.activity.addnote.AddNoteComponent;
import com.example.notepad.ui.activity.category.CategoryComponent;
import com.example.notepad.ui.activity.favourite.FavouriteComponent;
import com.example.notepad.ui.activity.main.MainComponent;
import com.example.notepad.ui.activity.notepage.NotePageComponent;
import com.example.notepad.ui.activity.splash.SplashComponent;

import dagger.Component;

@ApplicationScoped
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    //Modules
    NotepadDatabase getNotepadDatabase();

    //Dao
    CategoryRepository getCategoryRepository();
    NoteRepository getNoteRepository();

    //Components
    SplashComponent.Builder splashComponent();

    MainComponent.Builder mainComponent();

    AddNoteComponent.Builder addNoteComponent();

    NotePageComponent.Builder addNotePageComponent();

    FavouriteComponent.Builder addFavouriteComponent();

    CategoryComponent.Builder addCategoryComponent();

}
