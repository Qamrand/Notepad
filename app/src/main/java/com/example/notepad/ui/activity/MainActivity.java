package com.example.notepad.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad.R;
import com.example.notepad.data.Note;
import com.example.notepad.di.AppModule;
import com.example.notepad.di.DaggerAppComponent;
import com.example.notepad.di.RoomModule;
import com.example.notepad.repository.NoteRepository;
import com.example.notepad.ui.adapter.NoteListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //Declaring variables through the ButterKnife library
    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;

    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.recycler_view_note_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.text_toolbar_title)
    TextView mTextView;

    @Inject
    public NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTextView.setText(R.string.app_name);
        setSupportActionBar(mToolbar);

        //add recycler view on page
        addNoteRecyclerView();

        //add bottom navigation on page
        addBottomNavigationView();

        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build().inject(this);

        noteRepository.getAllNotes().observe(this,
                notes -> Toast
                        .makeText(MainActivity.this, "Testing text!!!!", Toast.LENGTH_SHORT)
                        .show());
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
            gridLayoutManager = new GridLayoutManager(this, 2);
        else
            gridLayoutManager = new GridLayoutManager(this, 4);

        gridLayoutManager.supportsPredictiveItemAnimations();
        //set GridLayoutManager in RecyclerView
        mRecyclerView.setLayoutManager(gridLayoutManager);

        //creating adapter class and set adapter in RecyclerView
        NoteListAdapter noteListAdapter = new NoteListAdapter(this, list);
        mRecyclerView.setAdapter(noteListAdapter);
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
                    startActivity(new Intent(this, AddNoteActivity.class));
                    break;
                case R.id.menu_bottom_favorite:
                    startActivity(new Intent(this, FavouriteActivity.class));
                    break;
            }
            return true;
        });
    }

}