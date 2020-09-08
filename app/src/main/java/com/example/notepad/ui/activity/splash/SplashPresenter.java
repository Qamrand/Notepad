package com.example.notepad.ui.activity.splash;

import android.app.Activity;

import com.example.notepad.BaseContract;

import java.util.Timer;
import java.util.TimerTask;


public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;

    //display time in milliseconds
    private final long SPLASH_SCREEN_DELAY = 500;

    public SplashPresenter() {
    }

    @Override
    public void onSplashInit() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                onTimeFinished();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    /**
     * load MainActivity after timer expires
     */
    @Override
    public void onTimeFinished() {
        mView.loadMain();
    }

    /**
     * @param view the view associated with this presenter
     */
    @Override
    public void setView(BaseContract.View view) {
        mView = (SplashContract.View) view;
    }

    /**
     * @param activity Drops the reference to the view when destroyed
     */
    @Override
    public void dropView(Activity activity) {
        activity.finish();
    }
}
