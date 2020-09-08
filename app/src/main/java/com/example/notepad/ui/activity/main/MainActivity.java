package com.example.notepad.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.MyApplication;
import com.example.notepad.R;
import com.example.notepad.database.entity.Note;
import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.di.scopes.ActivityScoped;
import com.example.notepad.di.scopes.ApplicationScoped;
import com.example.notepad.ui.activity.addnote.AddNoteActivity;
import com.example.notepad.ui.activity.category.CategoryActivity;
import com.example.notepad.ui.activity.favourite.FavouriteActivity;
import com.example.notepad.ui.activity.search.SearchActivity;
import com.example.notepad.ui.adapter.NoteListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainComponent mMainComponent;
    private String mTitle;

    //Declaring variables through the ButterKnife library
    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;
    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.recycler_view_note_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.text_toolbar_title)
    TextView toolbarName;

    //Dagger2
    @Inject
    NoteListAdapter mNoteListAdapter;

    @Inject
    @ApplicationScoped
    Context mContextApplication;

    @Inject
    @ActivityScoped
    Context mContextActivity;

    @Inject
    MainContract.Presenter mPresenter;

    public static final String EXTRA_CATEGORY_NAME = "Notepad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //butterknife init
        ButterKnife.bind(this);

        //injection
        mMainComponent = MyApplication.appComponent
                .mainComponent()
                .activityModule(new ActivityModule(this))
                .build();
        mMainComponent.inject(this);

        mPresenter.setView(this);

        //set title in toolbar. Title's name = category
        Bundle extras = getIntent().getExtras();
        if (extras == null || Objects.requireNonNull(extras.getString(EXTRA_CATEGORY_NAME)).equals("All notes")) {
            mTitle = "Notepad";
            mPresenter.loadData();
        } else {
            mTitle = extras.getString(EXTRA_CATEGORY_NAME);
            mPresenter.loadDataByCategory(mTitle);
        }

        toolbarName.setText(mTitle);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolbar);

        //add recycler view on page
        addNoteRecyclerView();

        //add bottom navigation on page
        addBottomNavigationView();

    }

    private void addNoteRecyclerView() {
        //set fixed size for recyclerview
        mRecyclerView.setHasFixedSize(true);
        //set GridLayoutManager in RecyclerView
        mRecyclerView.setLayoutManager(mNoteListAdapter.setGridLayoutManager(this));
        //creating adapter class and set adapter in RecyclerView
        mRecyclerView.setAdapter(mNoteListAdapter);
    }

    //add BottomNavigationView in activity
    private void addBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.menu_bottom_categories:
                    intent = new Intent(this, CategoryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
                case R.id.menu_bottom_search:
                    startActivity(new Intent(this, SearchActivity.class));
                    break;
                case R.id.menu_bottom_add:
                    intent = new Intent(this, AddNoteActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.putExtra(AddNoteActivity.EXTRA_CATEGORY_NAME, mTitle);
                    startActivity(intent);
                    break;
                case R.id.menu_bottom_favorite:
                    intent = new Intent(this, FavouriteActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    break;
            }
            return true;
        });
    }

    @Override
    public void showData(List<Note> notes) {
        mNoteListAdapter.setData(notes);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContextApplication, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if (extras == null || Objects.requireNonNull(extras.getString(EXTRA_CATEGORY_NAME)).equals("All notes")) {
            mPresenter.loadData();
        } else if (Objects.requireNonNull(extras.getString(EXTRA_CATEGORY_NAME)).equals("No category")) {
            mPresenter.loadDataByCategory("");
        } else {
            mPresenter.loadDataByCategory(mTitle);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //system back button disable animation
        overridePendingTransition(0, 0);
        mNoteListAdapter.clearData();
    }
}