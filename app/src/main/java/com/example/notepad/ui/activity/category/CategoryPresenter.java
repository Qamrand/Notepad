package com.example.notepad.ui.activity.category;

import android.app.Activity;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.notepad.BaseContract;
import com.example.notepad.R;
import com.example.notepad.database.entity.Category;
import com.example.notepad.ui.adapter.CategoryAdapter;
import com.example.notepad.ui.fragment.DialogAddCategoryFragment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.Model mModel;
    private CategoryContract.View mView;
    private List<Category> mCategoryList;

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
                            if (categories.isEmpty()) {
                                mModel.insertCategory(new Category("All notes"));
                                mModel.insertCategory(new Category("No category"));
                            }
                            mCategoryList = categories;
                            mView.showData(categories);
                        },
                        error -> mView.showError(error.getMessage())).dispose();
    }

    @Override
    public void removeCategory(int categoryId) {
        mModel.deleteCategory(categoryId);
    }

    @Override
    public void addNewCategory(CategoryActivity categoryActivity, Category category) {
        DialogAddCategoryFragment addCategoryFragment = new DialogAddCategoryFragment();

        if (category != null)
            addCategoryFragment.setCategory(category);

        addCategoryFragment
                .show(categoryActivity.getSupportFragmentManager(), "DialogAddCategoryFragment");
    }

    public CategoryActivity getCategoryActivity() {
        return (CategoryActivity) mView;
    }

    @Override
    public CategoryAdapter changeEditButtonMenu(Context context, MenuItem item, Button mAddCategoryButton) {
        CategoryAdapter categoryAdapter;

        if (item.getTitle().equals("EDIT")) {
            item.setTitle("SAVE");
            mAddCategoryButton.setVisibility(View.INVISIBLE);
            mAddCategoryButton.setClickable(false);
            mAddCategoryButton.setBackgroundColor(context.getResources().getColor(R.color.colorDarkGrey));
            categoryAdapter = new CategoryAdapter(context, true, this);
        } else {
            item.setTitle("EDIT");
            mAddCategoryButton.setVisibility(View.VISIBLE);
            mAddCategoryButton.setClickable(true);
            mAddCategoryButton.setBackgroundColor(context.getResources().getColor(R.color.colorBlackGrey));
            categoryAdapter = new CategoryAdapter(context, false, this);
        }
        categoryAdapter.setData(mCategoryList);
        return categoryAdapter;
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
