package com.example.notepad.ui.activity.splash;

import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Subcomponent;

@ActivityScoped
@Subcomponent(modules = {SplashModule.class, ActivityModule.class} )
public interface SplashComponent {

    void inject(SplashActivity splashActivity);

    @Subcomponent.Builder
    interface Builder {
        Builder splashModule(SplashModule module);
        Builder activityModule(ActivityModule module);
        SplashComponent build();
    }

}
