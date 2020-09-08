package com.example.notepad.ui.adapter;

import android.content.Context;

import com.example.notepad.di.scopes.ActivityScoped;
import com.example.notepad.ui.activity.category.CategoryContract;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @Provides
    @ActivityScoped
    public NoteListAdapter provideNoteListAdapter(Context context){
        return new NoteListAdapter(context);
    }

    @Provides
    @ActivityScoped
    public CategoryAdapter provideCategoryAdapter(Context context, CategoryContract.Presenter presenter) {
        return new CategoryAdapter(context, false, presenter);
    }

}
