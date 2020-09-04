package com.example.notepad.ui.activity.category;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.example.notepad.R;
import com.example.notepad.database.entity.Category;
import com.example.notepad.ui.adapter.CategoryAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.bottom_category_nav)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.recycler_view_category)
    RecyclerView mRecyclerView;

    @BindView(R.id.text_toolbar_title)
    TextView mToolbarName;

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
        List<Category> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            list.add(new Category(i, "Category " + i));
        }

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        LayoutAnimationController anim = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
        mRecyclerView.setLayoutAnimation(anim);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, list);
        mRecyclerView.setAdapter(categoryAdapter);
    }
}