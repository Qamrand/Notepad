package com.example.notepad.ui.activity.addnote;

import android.content.Context;
import android.content.Intent;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

public interface AddNoteContract {
    interface View extends BaseContract.View {
        String getName();

        String getDescriptionText();

        String getCategory();
    }

    interface Presenter extends BaseContract.Presenter {
        void onSaveClick(Context context, Intent intent);
    }

    interface Model extends BaseContract.Model {
        void addNote(Note note);
    }
}
