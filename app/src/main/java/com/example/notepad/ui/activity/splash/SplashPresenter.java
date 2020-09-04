package com.example.notepad.ui.activity.splash;

import android.app.Activity;

import com.example.notepad.BaseContract;

import java.util.Timer;
import java.util.TimerTask;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;

    private final long SPLASH_SCREEN_DELAY = 1500;

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

    @Override
    public void onTimeFinished() {
        mView.loadMain();
    }

    @Override
    public void setView(BaseContract.View view) {
        mView = (SplashContract.View) view;
    }

    @Override
    public void dropView(Activity activity) {
        activity.finish();
    }
}
