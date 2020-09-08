package com.example.notepad.ui.activity.notepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.notepad.BaseContract;
import com.example.notepad.R;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.activity.addnote.AddNoteActivity;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NotePagePresenter implements NotePageContract.Presenter {

    private NotePageContract.Model mModel;
    private NotePageContract.View mView;

    //for onClickTextSize method. Defines text large or small.
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
        Completable.fromAction(() -> {
            mModel.deleteNote(mNote);
            mView.loadMain();
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    public void onClickEditText(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(AddNoteActivity.EXTRA_NOTE_EDIT_PARAMETERS_ID, mNote);
        //only for fast init fields in AddActivity
        intent.putExtra(AddNoteActivity.EXTRA_NOTE_EDIT_PARAMETERS,
                new String[]{mNote.getMName(), mNote.getMText()});
        context.startActivity(intent);
    }

    @Override
    public void onClickFavourite(MenuItem item) {
        int favoriteNote = mNote.getIsFavourite();
        if (favoriteNote == 0) {
            item.setIcon(R.drawable.ic_favourite_note_full);
            favoriteNote = 1;
        } else {
            item.setIcon(R.drawable.ic_favourite_note_border);
            favoriteNote = 0;
        }
        mNote.setIsFavourite(favoriteNote);

        Completable.fromAction(() -> mModel.updateNote(mNote))
                .subscribeOn(Schedulers.io())
                .subscribe();
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
                        error -> mView.showError(error.getMessage())).dispose();
    }


    @Override
    public void setFavouriteIconMenu(MenuItem menuItem) {
        if (mNote.getIsFavourite() == 0)
            menuItem.setIcon(R.drawable.ic_favourite_note_border);
        else
            menuItem.setIcon(R.drawable.ic_favourite_note_full);
    }

    /**
     * @param view the view associated with this presenter
     */
    @Override
    public void setView(BaseContract.View view) {
        mView = (NotePageContract.View) view;
    }

    /**
     * @param activity Drops the reference to the view when destroyed
     */
    @Override
    public void dropView(Activity activity) {
        activity.finish();
    }

}
