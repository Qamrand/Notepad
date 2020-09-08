package com.example.notepad.ui.activity.main;

import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.di.scopes.ActivityScoped;
import com.example.notepad.ui.adapter.AdapterModule;

import dagger.Subcomponent;

@ActivityScoped
@Subcomponent(modules = {MainModule.class, ActivityModule.class, AdapterModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);

    @Subcomponent.Builder
    interface Builder {
        Builder mainModule(MainModule mainModule);

        Builder adapterModule(AdapterModule adapterModule);
        Builder activityModule(ActivityModule activityModule);
        MainComponent build();
    }
}
