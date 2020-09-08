package com.example.notepad.database.repository;

import com.example.notepad.database.entity.Category;

import java.util.List;

import io.reactivex.Flowable;

public interface CategoryRepository {


    Flowable<List<Category>> getAllCategories();

    void insertCategory(Category category);

    void deleteCategory(int categoryId);
}
