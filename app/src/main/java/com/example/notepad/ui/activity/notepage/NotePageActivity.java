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

public class NotePageActivity extends AppCompatActivity implements NotePageContract.View{

    public static final String EXTRA_NOTE_ID = "noteId";
    private final String TAG = "NotePageActivity";
    private NotePageComponent mNotePageComponent;

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

        mNotePageComponent = MyApplication.appComponent.addNotePageComponent().build();
        mNotePageComponent.inject(this);

        mPresenter.setView(this);

        int idNote = getIntent().getExtras().getInt(EXTRA_NOTE_ID);
        mPresenter.loadNoteData(idNote);


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
                mPresenter.onCLickRemoveNote();
                break;
            case R.id.menu_note_edit:
                mPresenter.onClickEditText();
                break;
            case R.id.menu_note_favourite:
                mPresenter.onClickFavourite(item);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /*TODO set favourite item.
        *
        * if (!addedToFavorites) {
        MenuItem mi = menu.findItem(R.id.ic_favorites_checked);
        menu.removeItem(R.id.ic_favorites_checked);
        mi.setIcon(R.drawable.ic_menu_favorites_unchecked);
        mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        addedToFavorites = true;

    } else {
        MenuItem mi = menu.findItem(R.id.ic_favorites_unchecked);
        menu.removeItem(R.id.ic_favorites_unchecked);
        mi.setIcon(R.drawable.ic_menu_favorites_checked);
        mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        addedToFavorites = false;
        * */
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void showNoteData(Note note) {
        noteNameTextView.setText(note.getMName());
        noteCategoryTextView.setText(note.getMCategory());
        noteContentTextView.setText(note.getMText());
    }

    @Override
    public void loadMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, message);
    }
}