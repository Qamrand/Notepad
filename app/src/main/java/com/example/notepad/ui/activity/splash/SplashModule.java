package com.example.notepad.ui.activity.splash;

import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    @Provides
    @ActivityScoped
    SplashContract.Presenter provideMainPresenter() {
        return new SplashPresenter();
    }

}
