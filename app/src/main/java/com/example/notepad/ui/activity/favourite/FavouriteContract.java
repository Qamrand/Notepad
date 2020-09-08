package com.example.notepad.ui.activity.favourite;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

import java.util.List;

import io.reactivex.Flowable;

public interface FavouriteContract {

    interface View extends BaseContract.View {
        /**
         * show favourite notes
         */
        void showFavouriteNotes(List<Note> list);
        /**
         * show error message in logs
         */
        void showError(String message);
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * pass data from model to view
         */
        void loadFavouriteNotes();
    }

    interface Model extends BaseContract.Model {
        /**
         * load notes list from db
         */
        Flowable<List<Note>> getFavouriteNotes();
    }
}
