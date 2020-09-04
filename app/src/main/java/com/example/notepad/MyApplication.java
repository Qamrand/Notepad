package com.example.notepad;

import android.app.Activity;
import android.app.Application;

import com.example.notepad.database.NotepadDatabase;
import com.example.notepad.database.entity.Category;
import com.example.notepad.database.entity.CategoryDao;
import com.example.notepad.database.entity.NoteDao;
import com.example.notepad.database.repository.CategoryRepository;
import com.example.notepad.database.repository.NoteRepository;
import com.example.notepad.di.components.AppComponent;
import com.example.notepad.di.components.DaggerAppComponent;
import com.example.notepad.di.modules.AppModule;
import com.example.notepad.di.modules.RoomModule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyApplication extends Application {

    public static AppComponent appComponent;

    public static NotepadDatabase db;

    public static CategoryRepository categoryRepository;

    public static NoteRepository noteRepository;

    public MyApplication() {
    }

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //initialize dagger2

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .roomModule(new RoomModule(this))
                .build();

        categoryRepository = appComponent.getCategoryRepository();

        noteRepository = appComponent.getNoteRepository();

    }
}
