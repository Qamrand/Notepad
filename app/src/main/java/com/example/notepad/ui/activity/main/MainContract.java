package com.example.notepad.ui.activity.main;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

import java.util.List;

import io.reactivex.Flowable;

public interface MainContract {

    interface Model extends BaseContract.Model{
        Flowable showAllNotes();
    }

    interface Presenter extends BaseContract.Presenter {
        void loadData();
    }

    interface View extends BaseContract.View{
        void showData(List<Note> notes);

        void showError(String message);

        void showComplete();

        void onResume();
    }
}
