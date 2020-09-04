package com.example.notepad.ui.activity.favourite;

import com.example.notepad.BaseContract;
import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouriteModule {

    @Provides
    @ActivityScoped
    public FavouriteContract.Model provideFavouriteModel() {
        return new FavouriteModel();
    }

    @Provides
    @ActivityScoped
    public FavouriteContract.Presenter provideFavouritePresenter(FavouriteContract.Model model) {
        return new FavouritePresenter(model);
    }
}
