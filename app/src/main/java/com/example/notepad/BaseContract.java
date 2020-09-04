package com.example.notepad;

import android.app.Activity;
import android.view.View;

public interface BaseContract {

    interface Model {

    }

    interface Presenter {

        /**
         *  Binds presenter with a view when resumed. The Presenter will perform initialization here.
         *
         * @param view the view associated with this presenter
         */
        void setView(View view);

        /**
         *Drops the reference to the view when destroyed
         */
        void dropView(Activity activity);
    }

    interface View {

    }
}
