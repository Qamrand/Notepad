package com.example.notepad.ui.activity.category;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.MyApplication;
import com.example.notepad.R;
import com.example.notepad.database.entity.Category;
import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.ui.adapter.CategoryAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements CategoryContract.View{

    private CategoryComponent mCategoryComponent;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view_category)
    RecyclerView mRecyclerView;

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarName;

    @BindView(R.id.add_category_button)
    Button mAddCategoryButton;

    @Inject
    CategoryAdapter categoryAdapter;

    @Inject
    CategoryContract.Presenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //init butterknife
        ButterKnife.bind(this);

        mCategoryComponent = MyApplication.appComponent
                .addCategoryComponent()
                .activityModule(new ActivityModule(this))
                .build();

        mCategoryComponent.inject(this);

        mPresenter.setView(this);

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        //set toolbar title
        mToolbarName.setText(R.string.category_notes);
        //set toolbar menu
        setSupportActionBar(mToolbar);
        //set back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        addNoteRecyclerView();

        mAddCategoryButton.setOnClickListener(click ->
                mPresenter.addNewCategory(this, null));
    }

    public CategoryContract.Presenter getPresenter(){
        return mPresenter;
    }

    private void addNoteRecyclerView() {

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        /*LayoutAnimationController anim = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
        mRecyclerView.setLayoutAnimation(anim);*/
        mRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_edit_category) {
            categoryAdapter = mPresenter.changeEditButtonMenu(this, item, mAddCategoryButton);
            mRecyclerView.setAdapter(categoryAdapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showData(List<Category> categories) {
        categoryAdapter.setData(categories);
    }

    @Override
    public void showError(String message) {
        Log.e("TODO", message);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        categoryAdapter.clearData();
    }
}