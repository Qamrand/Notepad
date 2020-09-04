package com.example.notepad.ui.activity.notepage;

import android.app.Activity;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.notepad.BaseContract;
import com.example.notepad.R;
import com.example.notepad.database.entity.Note;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NotePagePresenter implements NotePageContract.Presenter {

    private NotePageContract.Model mModel;
    private NotePageContract.View mView;

    boolean isLargeText = false;
    private Note mNote;

    @Inject
    public NotePagePresenter(NotePageContract.Model model) {
        mModel = model;
    }

    @Override
    public void onClickTextSize(TextView noteContentTextView) {
        if (!isLargeText)
            noteContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        else {
            noteContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        }
        isLargeText = !isLargeText;
    }

    @Override
    public void onClickSearch() {

    }

    @Override
    public void onCLickRemoveNote() {
        mModel.deleteNote(mNote);
        mView.loadMain();
    }

    @Override
    public void onClickEditText() {

    }

    @Override
    public void onClickFavourite(MenuItem item) {
        int favoriteNote = mNote.getIsFavourite();
        if(favoriteNote == 0) {
            item.setIcon(R.drawable.ic_favourite_note_full);
            favoriteNote = 1;
        } else {
            item.setIcon(R.drawable.ic_favourite_note_border);
            favoriteNote = 0;
        }
        mNote.setIsFavourite(favoriteNote);
        mModel.updateNote(mNote);
    }

    @Override
    public void loadNoteData(int id) {
        mModel.getNoteById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(note -> {
                            mView.showNoteData(note);
                            mNote = note;
                        },
                        error -> mView.showError(error.getMessage()));
    }

    @Override
    public void setView(BaseContract.View view) {
        mView = (NotePageContract.View) view;
    }

    @Override
    public void dropView(Activity activity) {
        activity.finish();
    }

}
