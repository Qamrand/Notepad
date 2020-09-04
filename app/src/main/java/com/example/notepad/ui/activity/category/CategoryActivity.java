package com.example.notepad.ui.activity.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.example.notepad.R;
import com.example.notepad.database.entity.Category;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.adapter.CategoryAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements CategoryContract.View{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view_category)
    RecyclerView mRecyclerView;

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarName;

    @Inject
    CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //init butterknife
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        //set toolbar title
        mToolbarName.setText(R.string.category_notes);
        //set toolbar menu
        setSupportActionBar(mToolbar);
        //set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        addNoteRecyclerView();
    }

    private void addNoteRecyclerView() {

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        LayoutAnimationController anim = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
        mRecyclerView.setLayoutAnimation(anim);
        mRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_edit_category:
                if(item.getTitle().equals("EDIT")) {
                    categoryAdapter = new CategoryAdapter(this, true);
                    item.setTitle("SAVE");
                } else {
                    categoryAdapter = new CategoryAdapter(this, false);
                    item.setTitle("EDIT");
                }
                mRecyclerView.setAdapter(categoryAdapter);
                break;
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
    public String getCategoryName() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}