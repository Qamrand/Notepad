package com.example.notepad.ui.activity.addnote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.notepad.BaseContract;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.activity.main.MainActivity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class AddNotePresenter implements AddNoteContract.Presenter {

    public static final String TAG = "AddNoteActivity";

    private AddNoteContract.Model mModel;
    private AddNoteContract.View mView;

    private Note mNote;

    @Inject
    public AddNotePresenter(AddNoteContract.Model model) {
        mModel = model;
    }

    @Override
    public void onSaveClick(Context context, Intent intent) {
        Serializable serializableNote = intent.getSerializableExtra(AddNoteActivity.EXTRA_NOTE_EDIT_PARAMETERS_ID);
        String nameNote = mView.getName();
        String descriptionTextNote = mView.getDescriptionText();
        String category = mView.getCategory();
        if(category.equals("Notepad") || category.equals("No category"))
            category = "";

        if (nameNote.equals("") || descriptionTextNote.equals("")) {
            return;
        }
        Log.d(TAG, nameNote);
        Log.d(TAG, descriptionTextNote);
        //Log.d(TAG, category);

        String date = getDate();
        if (serializableNote == null) {
            mNote = new Note(nameNote, descriptionTextNote, category, date, date);
        } else {
            mNote = (Note) serializableNote;
            mNote.setMName(nameNote);
            mNote.setMText(descriptionTextNote);
        }
        Log.d(TAG, mNote.toString());


        Completable.fromAction(() -> mModel.addNote(mNote))
                .subscribeOn(Schedulers.io())
                .subscribe();

        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(mainActivityIntent);
    }

    public void setNote(Note note) {
        mNote = note;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return dateFormat.format(calendar.getTime());
    }
}
