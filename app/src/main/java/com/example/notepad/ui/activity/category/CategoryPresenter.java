package com.example.notepad.ui.activity.category;

import android.app.Activity;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Category;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.Model mModel;
    private CategoryContract.View mView;

    @Inject
    public CategoryPresenter(CategoryContract.Model model) {
        mModel = model;
    }

    @Override
    public void loadData() {
        mModel.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                            mView.showData(categories);
                        },
                        error -> mView.showError(error.getMessage()));
    }

    @Override
    public void removeCategory(Category category) {
        mModel.deleteCategory(category);
    }

    @Override
    public void setView(BaseContract.View view) {
        mView = (CategoryContract.View) view;
    }

    @Override
    public void dropView(Activity activity) {
        activity.finish();
    }
}
