package com.example.notepad.ui.activity.main;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

import java.util.List;

import io.reactivex.Flowable;

public interface MainContract {

    interface Model extends BaseContract.Model{
        Flowable<List<Note>> getAllNotes();

        Flowable<List<Note>> getAllNotesByCategory(String category);
    }

    interface Presenter extends BaseContract.Presenter {
        void loadData();

        void loadDataByCategory(String category);
    }

    interface View extends BaseContract.View{
        void showData(List<Note> notes);

        void showError(String message);

        void onResume();
    }
}
