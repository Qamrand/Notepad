package com.example.notepad.ui.activity.favourite;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.MyApplication;
import com.example.notepad.R;
import com.example.notepad.database.entity.Note;
import com.example.notepad.di.modules.ActivityModule;
import com.example.notepad.ui.adapter.NoteListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteActivity extends AppCompatActivity implements FavouriteContract.View{

    private static final String TAG = "FavouriteActivity";
    private FavouriteComponent mFavouriteComponent;

    @BindView(R.id.recycler_view_note_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar_main)
    Toolbar mToolbar;

    @BindView(R.id.text_toolbar_title)
    TextView toolbarName;

    @Inject
    NoteListAdapter mNoteListAdapter;

    @Inject
    FavouriteContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ButterKnife.bind(this);

        mFavouriteComponent = MyApplication.appComponent
                .addFavouriteComponent()
                .activityModule(new ActivityModule(this))
                .build();
        mFavouriteComponent.inject(this);

        mPresenter.setView(this);

        toolbarName.setText(R.string.favourite_notes);

        addNoteRecyclerView();
        //Log.d(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    private void addNoteRecyclerView() {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mNoteListAdapter.setGridLayoutManager(this));
        mRecyclerView.setAdapter(mNoteListAdapter);
    }

    @Override
    public void showFavouriteNotes(List<Note> list) {
        mNoteListAdapter.setData(list);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.loadFavouriteNotes();
        //.d(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.d(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mNoteListAdapter.clearData();
        //Log.d(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.d(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }
}