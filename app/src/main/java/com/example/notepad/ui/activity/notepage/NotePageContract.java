package com.example.notepad.ui.activity.notepage;

import android.content.Context;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

import io.reactivex.Single;

public interface NotePageContract {

    interface View extends BaseContract.View {

        /**
         * init fields
         */
        void showNoteData(Note note);

        /**
         * Return to the homepage.
         * Set MainAcitivity to the beginning of the stack
         */
        void loadMain();

        /**
         * show error message in logs
         */
        void showError(String message);
    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * change text size in note description
         * @param noteContentTextView it is note content
         */
        void onClickTextSize(TextView noteContentTextView);

        /**
         * search note
         */
        void onClickSearch();

        /**
         * remove note
         */
        void onCLickRemoveNote();

        /**
         * edit note data
         * @param context open edit page(AddNoteActivity)
         */
        void onClickEditText(Context context);

        /**
         * change item icon after click and write changes to db
         * @param item - favourite menu item
         */
        void onClickFavourite(MenuItem item);

        /**
         * load note data from db, pass Note list to View and init mNote variable
         * @param id - note id
         */
        void loadNoteData(int id);

        /**
         * init menuItem icon
         */
        void setFavouriteIconMenu(MenuItem menuItem);
    }

    interface Model extends BaseContract.Model {

        /**
         * @return a note by id or show error exception
         */
        Single<Note> getNoteById(int id);

        /**
         * @param note update date
         */
        void updateNote(Note note);

        /**
         * @param note delete date
         */
        void deleteNote(Note note);
    }
}
