package com.example.notepad.ui.activity.category;

import com.example.notepad.MyApplication;
import com.example.notepad.database.entity.Category;

import java.util.List;

import io.reactivex.Flowable;

public class CategoryModel implements CategoryContract.Model{

    @Override
    public Flowable<List<Category>> getAllCategories() {
        return MyApplication.categoryRepository.getAllCategories();
    }

    @Override
    public void deleteCategory(Category category) {
        MyApplication.categoryRepository.deleteCategory(category);
    }
}
