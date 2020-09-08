package com.example.notepad.ui.activity.favourite;

import android.app.Activity;

import com.example.notepad.BaseContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FavouritePresenter implements FavouriteContract.Presenter {

    private FavouriteContract.Model mModel;
    private FavouriteContract.View mView;

    @Inject
    public FavouritePresenter(FavouriteContract.Model model) {
        mModel = model;
    }

    @Override
    public void loadFavouriteNotes() {
        mModel.getFavouriteNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> mView.showFavouriteNotes(notes),
                        error -> mView.showError(error.getMessage()))
                .dispose();
    }

    @Override
    public void setView(BaseContract.View view) {
        mView = (FavouriteContract.View) view;
    }

    @Override
    public void dropView(Activity activity) {
        activity.finish();
    }
}
