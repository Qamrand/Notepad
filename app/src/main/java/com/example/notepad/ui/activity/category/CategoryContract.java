package com.example.notepad.ui.activity.category;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Category;
import com.example.notepad.database.entity.Note;

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

        void removeCategory(Category category);
    }

    interface Model extends BaseContract.Model {
        Flowable<List<Category>> getAllCategories();

        void deleteCategory(Category category);
    }
}
