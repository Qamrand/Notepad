package com.example.notepad.ui.activity.favourite;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

import java.util.List;

import io.reactivex.Flowable;

public interface FavouriteContract {

    interface View extends BaseContract.View {
        void showFavouriteNotes(List<Note> list);

        void showError(String message);

    }

    interface Presenter extends BaseContract.Presenter {
        void loadFavouriteNotes();
    }

    interface Model extends BaseContract.Model {
        Flowable<List<Note>> getFavouriteNotes();
    }
}
