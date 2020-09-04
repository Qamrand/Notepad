package com.example.notepad.ui.activity.favourite;

import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.di.scopes.ActivityScoped;
import com.example.notepad.ui.adapter.AdapterModule;

import dagger.Subcomponent;

@ActivityScoped
@Subcomponent(modules = {ActivityModule.class, FavouriteModule.class, AdapterModule.class})
public interface FavouriteComponent {

    void inject(FavouriteActivity favouriteActivity);

    @Subcomponent.Builder
    interface Builder {
        Builder activityModule(ActivityModule activityModule);
        Builder favouriteModule(FavouriteModule favouriteModule);
        Builder adapterModule(AdapterModule adapterModule);
        FavouriteComponent build();
    }
}
