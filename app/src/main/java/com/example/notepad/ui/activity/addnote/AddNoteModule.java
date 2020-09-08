package com.example.notepad.ui.activity.addnote;

import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class AddNoteModule {

    @Provides
    @ActivityScoped
    AddNoteContract.Presenter provideMainPresenter(AddNoteContract.Model model){
        return new AddNotePresenter(model);
    }

    @Provides
    @ActivityScoped
    AddNoteContract.Model provideMainModel(){
        return new AddNoteModel();
    }
}
