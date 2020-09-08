package com.example.notepad.ui.activity.category;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Button;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Category;
import com.example.notepad.ui.adapter.CategoryAdapter;

import java.util.List;

import io.reactivex.Flowable;

public interface CategoryContract {

    interface View extends BaseContract.View {
        void showData(List<Category> notes);

        void showError(String message);

        String getCategoryName();

        void onResume();
    }

    interface Presenter extends BaseContract.Presenter {
        void loadData();

        void removeCategory(int categoryId);

        void addNewCategory(CategoryActivity categoryActivity, Category category);

        CategoryActivity getCategoryActivity();

        CategoryAdapter changeEditButtonMenu(Context context, MenuItem item, Button mAddCategoryButton);
    }

    interface Model extends BaseContract.Model {
        Flowable<List<Category>> getAllCategories();

        void deleteCategory(int categoryId);

        void insertCategory(Category category);
    }
}
