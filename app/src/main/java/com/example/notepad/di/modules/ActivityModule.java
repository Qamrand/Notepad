package com.example.notepad.di.modules;

import android.app.Activity;
import android.content.Context;

import com.example.notepad.di.scopes.ActivityScoped;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(@NotNull final Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityScoped
    Activity provideActivity(){
        return mActivity;
    }

    @Provides
    @ActivityScoped
    Context provideActivityContext(){
        return mActivity;
    }
}
