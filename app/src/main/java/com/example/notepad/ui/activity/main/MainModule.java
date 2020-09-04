package com.example.notepad.ui.activity.main;

import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    @ActivityScoped
    MainContract.Presenter provideMainPresenter(MainContract.Model model){
        return new MainPresenter(model);
    }

    @Provides
    @ActivityScoped
    MainContract.Model provideMainModel(){
        return new MainModel();
    }
}
