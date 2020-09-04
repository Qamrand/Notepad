package com.example.notepad.ui.activity.notepage;

import android.content.Context;
import android.widget.TextView;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

import io.reactivex.Single;

public interface NotePageContract {

    interface View extends BaseContract.View {

        void showNoteData(Note note);

        void loadMain();

        void showError(String message);
    }

    interface Presenter extends BaseContract.Presenter {

        void onClickTextSize(TextView noteContentTextView);

        void onClickSearch();

        void onCLickRemoveNote(Context context);

        void onClickEditText(Context context);

        void onClickFavourite();

        void loadNoteData();
    }

    interface Model extends BaseContract.Model {

        Single<Note> getNoteById(int id);

        void updateNote(Note note);

        void deleteNote(Note note);
    }
}
