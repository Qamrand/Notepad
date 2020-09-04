package com.example.notepad.ui.activity.notepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.notepad.MyApplication;
import com.example.notepad.R;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.activity.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NotePageActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE_ID = "noteId";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.note_name)
    TextView noteNameTextView;

    @BindView(R.id.note_category)
    TextView noteCategoryTextView;

    @BindView(R.id.note_content)
    TextView noteContentTextView;

    @Inject
    NotePageContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);
        //init butterknife
        ButterKnife.bind(this);
        //set toolbar menu
        setSupportActionBar(mToolbar);
        //Set back
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int idNote = getIntent().getExtras().getInt(EXTRA_NOTE_ID);
        MyApplication.noteRepository.getNoteById(idNote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        note -> {
                            noteNameTextView.setText(note.getMName());
                            noteCategoryTextView.setText(note.getMCategory());
                            noteContentTextView.setText(note.getMText());
                        },
                        error -> Log.e("NotePageActivity", "getNoteById exception error"));

        MyApplication.appComponent.getNoteRepository().getAllNotes();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_note_text_size:
                mPresenter.onClickTextSize(noteContentTextView);
                break;
            case R.id.menu_note_search:
                mPresenter.onClickSearch();
                break;
            case R.id.menu_note_delete:
                mPresenter.onCLickRemoveNote(this);
                break;
            case R.id.menu_note_edit:
                mPresenter.onClickEditText(this);
                break;
            case R.id.menu_note_favourite:
                mPresenter.onClickFavourite();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}