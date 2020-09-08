package com.example.notepad.ui.activity.splash;

import com.example.notepad.BaseContract;

public interface SplashContract {

    interface View extends BaseContract.View {
        /**
         * load MainActivity
         */
        void loadMain();
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * start delay timer
         * Simulate a long loading process on application startup.
         */

        void onSplashInit();

        /**
         * Finished timer. Start activity.
         */
        void onTimeFinished();
    }

    interface Model extends BaseContract.Model {

    }
}
