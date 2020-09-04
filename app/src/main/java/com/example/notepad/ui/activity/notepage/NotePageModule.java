package com.example.notepad.ui.activity.notepage;

import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class NotePageModule {

    @Provides
    @ActivityScoped
    NotePageContract.Presenter provideNotePagePresenter(NotePageContract.Model model){
        return new NotePagePresenter(model);
    }

    @Provides
    @ActivityScoped
    NotePageContract.Model provideNotePageModel(){
        return new NotePageModel();
    }
}
