package com.example.notepad.database.repository;

import com.example.notepad.database.entity.Category;
import com.example.notepad.database.entity.CategoryDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class CategoryDataSource implements CategoryRepository {

    private CategoryDao mCategoryDao;

    @Inject
    public CategoryDataSource(CategoryDao categoryDao) {
        this.mCategoryDao = categoryDao;
    }

    @Override
    public Flowable<List<Category>> getAllCategories() {
        return mCategoryDao.getAllCategories();
    }

    @Override
    public void insertCategory(Category category) {
        mCategoryDao.insertCategory(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        mCategoryDao.deleteCategory(categoryId);
    }
}
