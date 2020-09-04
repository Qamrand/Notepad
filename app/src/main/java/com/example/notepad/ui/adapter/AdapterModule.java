package com.example.notepad.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.di.scopes.ActivityScoped;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    @Provides
    @ActivityScoped
    public NoteListAdapter provideNoteListAdapter(Context context){
        return new NoteListAdapter(context);
    }



}
