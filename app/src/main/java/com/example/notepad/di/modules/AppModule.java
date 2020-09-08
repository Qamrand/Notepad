package com.example.notepad.di.modules;

import android.content.Context;

import com.example.notepad.MyApplication;
import com.example.notepad.di.qualifier.ApplicationContext;
import com.example.notepad.di.scopes.ApplicationScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Provide MyApplication class and Context
 */
@Module
public class AppModule {

    private MyApplication mMyApplication;

    public AppModule(MyApplication myApplication) {
        mMyApplication = myApplication;
    }

    @Provides
    @ApplicationScoped
    public MyApplication providesMyApplication(){
        return mMyApplication;
    }

    @Provides
    @ApplicationScoped
    @ApplicationContext
    public Context provideApplicationContext(){
        return mMyApplication;
    }
}
