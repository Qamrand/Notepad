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
        /**
         * @param categories set data in recyclerview
         */
        void showData(List<Category> categories);

        /**
         * show error message in logs
         */
        void showError(String message);

        void onResume();
    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * pass data from model to view
         */
        void loadData();

        void removeCategory(int categoryId);

        void addNewCategory(CategoryActivity categoryActivity, Category category);

        /**
         * @return CategoryActivity and pass this in CategoryAdapter
         */
        CategoryActivity getCategoryActivity();

        /**
         * create new CategoryAdapter based on clicking a menu item.
         *
         * @param context category activity context
         * @param item MenuItem can be in two states: edit and save
         * @param mAddCategoryButton setClickable/unClickable and setVisibility/ubVisibility
         *                           edit buttons in recyclerview element
         * @return new CategoryAdapter depending on the options of the menu item
         */
        CategoryAdapter changeEditButtonMenu(Context context, MenuItem item, Button mAddCategoryButton);
    }

    interface Model extends BaseContract.Model {
        /**
         * @return categories list
         */
        Flowable<List<Category>> getAllCategories();

        /**
         * @param categoryId delete category by id
         */
        void deleteCategory(int categoryId);

        /**
         * @param category insert category
         */
        void insertCategory(Category category);
    }
}
