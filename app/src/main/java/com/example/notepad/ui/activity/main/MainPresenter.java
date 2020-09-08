package com.example.notepad.ui.activity.main;

import android.app.Activity;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    @Inject
    public MainPresenter(MainContract.Model model) {
        mModel = model;
    }

    @Override
    public void setView(BaseContract.View view) {
        mView = (MainContract.View) view;
    }

    @Override
    public void dropView(Activity activity) {
        activity.finish();
    }

    @Override
    public void loadData() {
        mModel.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> mView.showData(notes),
                        throwable -> mView.showError(throwable.toString())).dispose();
    }

    @Override
    public void loadDataByCategory(String category) {
        mModel.getAllNotesByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> mView.showData(notes),
                        throwable -> mView.showError(throwable.toString())).dispose();
    }

}
