package com.example.notepad.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainComponent mMainComponent;

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

    public static String EXTRA_CATEGORY_NAME = "Notepad";

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

        Bundle extras = getIntent().getExtras();

        //set title in toolbar. Title's name = category
        String titleName;
        if (extras == null) {
            titleName = "Notepad";
        } else {
            titleName = (String) extras.get(EXTRA_CATEGORY_NAME);
        }
        toolbarName.setText(titleName);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolbar);

        //add recycler view on page
        addNoteRecyclerView();

        //add bottom navigation on page
        addBottomNavigationView();

    }

    private void addNoteRecyclerView() {
        List<Note> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            list.add(new Note(i, "Note" + i, "asasd",
                    R.drawable.ic_favourite_note_border, "Categ" + i,
                    "ads", "dsaads"));
        }
        //set fixed size for recyclerview
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager;
        //if the orientation is portrait - draw two columns in the GridLayoutManager
        //if the orientation is landscape - three columns
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            gridLayoutManager = new GridLayoutManager(mContextActivity, 2);
        else
            gridLayoutManager = new GridLayoutManager(mContextActivity, 4);

        //set GridLayoutManager in RecyclerView
        mRecyclerView.setLayoutManager(gridLayoutManager);
        //creating adapter class and set adapter in RecyclerView
        mRecyclerView.setAdapter(mNoteListAdapter);
    }

    //add BottomNavigationView in activity
    private void addBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_bottom_categories:
                    startActivity(new Intent(this, CategoryActivity.class));
                    break;
                case R.id.menu_bottom_search:
                    startActivity(new Intent(this, SearchActivity.class));
                    break;
                case R.id.menu_bottom_add:
                    Intent intent = new Intent(this, AddNoteActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.putExtra(AddNoteActivity.EXTRA_CATEGORY_NAME, "No category");
                    startActivity(intent);
                    break;
                case R.id.menu_bottom_favorite:
                    startActivity(new Intent(this, FavouriteActivity.class));
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
        Toast.makeText(mContextApplication, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showComplete() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNoteListAdapter.clearData();
    }
}