package com.example.notepad.ui.activity.notepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.notepad.BaseContract;
import com.example.notepad.MyApplication;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.activity.main.MainActivity;

public class NotePagePresenter implements NotePageContract.Presenter {

    private NotePageContract.Model mModel;
    private NotePageContract.View mView;

    boolean isLargeText = false;
    private Note mNote;

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
    public void onCLickRemoveNote(Context context) {
        mModel.deleteNote(mNote);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    public void onClickEditText(Context context) {

    }

    @Override
    public void onClickFavourite() {

    }

    @Override
    public void loadNoteData() {

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
