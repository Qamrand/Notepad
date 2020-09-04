package com.example.notepad.ui.activity.notepage;

import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Subcomponent;

@ActivityScoped
@Subcomponent(modules = {ActivityModule.class, NotePageModule.class})
public interface NotePageComponent {

    void inject(NotePageActivity notePageActivity);

    @Subcomponent.Builder
    interface Builder {
        Builder activityModule(ActivityModule activityModule);
        Builder notePageModule(NotePageModule notePageModule);
        NotePageComponent build();
    }


}
