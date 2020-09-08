package com.example.notepad.database.entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category")
    Flowable<List<Category>> getAllCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(Category category);

    @Query("DELETE from category WHERE id IN (:categoryId)")
    void deleteCategory(int categoryId);
}
