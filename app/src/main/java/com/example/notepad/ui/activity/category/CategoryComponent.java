package com.example.notepad.ui.activity.category;

import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.di.scopes.ActivityScoped;
import com.example.notepad.ui.adapter.AdapterModule;

import dagger.Subcomponent;

@ActivityScoped
@Subcomponent(modules = {ActivityModule.class, AdapterModule.class, CategoryModule.class})
public interface CategoryComponent {

    void inject(CategoryActivity categoryActivity);

    @Subcomponent.Builder
    interface Builder {
        Builder activityModule(ActivityModule activityModule);
        Builder adapterModule(AdapterModule adapterModule);
        Builder categoryModule(CategoryModule categoryModule);
        CategoryComponent build();
    }
}
