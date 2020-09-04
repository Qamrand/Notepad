package com.example.notepad.ui.activity.addnote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.activity.main.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AddNotePresenter implements AddNoteContract.Presenter {

    public static final String TAG = "ADD_NOTE_ACTIVITY";

    private AddNoteContract.Model mModel;
    private AddNoteContract.View mView;

    @Inject
    public AddNotePresenter(AddNoteContract.Model model) {
        mModel = model;
    }

    @Override
    public void onSaveClick(Context context) {
        String nameNote = mView.getName();
        String descriptionTextNote = mView.getDescriptionText();
        String category = mView.getCategory();

        if(nameNote.equals("") || descriptionTextNote.equals("")) {
            return;
        }
        String date = getDate();
        Note note = new Note(nameNote, descriptionTextNote, category, date, date);

        Log.d(TAG, "noteClass = " + note.toString());

        Observable.just(note)
                .subscribeOn(Schedulers.io())
                .subscribe();

        mModel.addNote(note);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    public void setView(BaseContract.View view) {
        mView = (AddNoteContract.View) view;
    }

    @Override
    public void dropView(Activity activity) {
        activity.finish();
    }

    private String getDate() {
        Calendar calendar = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }
}
