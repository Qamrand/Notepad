package com.example.notepad.ui.activity.addnote;

import android.content.Context;
import android.content.Intent;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

public interface AddNoteContract {
    interface View extends BaseContract.View {
        /**
         * @return note name
         */
        String getName();

        /**
         * @return note content text
         */
        String getDescriptionText();

        /**
         * @return note category
         */
        String getCategory();
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * save new note
         * @param context go from AddNoteActivity.class
         * @param intent     to MainActivity.class
         */
        void onSaveClick(Context context, Intent intent);
    }

    interface Model extends BaseContract.Model {
        /**
         * add new note to db
         */
        void addNote(Note note);
    }
}
