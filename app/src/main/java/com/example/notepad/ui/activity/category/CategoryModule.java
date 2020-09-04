package com.example.notepad.ui.activity.category;

import com.example.notepad.di.scopes.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryModule {

    @Provides
    @ActivityScoped
    public CategoryContract.Model provideCategoryModel() {
        return new CategoryModel();
    }

    @Provides
    @ActivityScoped
    public CategoryContract.Presenter provideCategoryPresenter(CategoryContract.Model model) {
        return new CategoryPresenter(model);
    }
}
