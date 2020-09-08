package com.example.notepad.ui.activity.main;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

import java.util.List;

import io.reactivex.Flowable;

public interface MainContract {

    interface Model extends BaseContract.Model{
        /**
         * @return notes list
         */
        Flowable<List<Note>> getAllNotes();

        /**
         * @return notes list by category
         */
        Flowable<List<Note>> getAllNotesByCategory(String category);
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * load notes list from db
         */
        void loadData();

        /**
         * load notes list by category from db
         */
        void loadDataByCategory(String category);
    }

    interface View extends BaseContract.View{
        /**
         * init fields.
         */
        void showData(List<Note> notes);

        /**
         * show error message in logs
         */
        void showError(String message);

        void onResume();
    }
}
