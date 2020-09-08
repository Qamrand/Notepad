package com.example.notepad.ui.activity.addnote;

import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Subcomponent;

@ActivityScoped
@Subcomponent(modules = {ActivityModule.class, AddNoteModule.class})
public interface AddNoteComponent {

    void inject(AddNoteActivity addNoteActivity);

    @Subcomponent.Builder
    interface Builder {
        AddNoteComponent.Builder addNoteModule(AddNoteModule module);

        AddNoteComponent.Builder activityModule(ActivityModule module);

        AddNoteComponent build();
    }
}
