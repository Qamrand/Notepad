package com.example.notepad.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notepad.MyApplication;
import com.example.notepad.R;
import com.example.notepad.ui.activity.main.MainActivity;

import javax.inject.Inject;

/**
 * Calls a splash screen when loading an application
 */
public class SplashActivity extends AppCompatActivity implements SplashContract.View{

    private SplashComponent splashComponent;

    //init dagger
    @Inject
    SplashContract.Presenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //injection
        splashComponent = MyApplication.appComponent
                .splashComponent().build();
        splashComponent.inject(this);

        //pass view to the presenter
        mPresenter.setView(this);
        //start delay timer
        mPresenter.onSplashInit();
    }

    /**
     * Load MainActivity and destroy SplashActivity
     */

    @Override
    public void loadMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(intent);

        //onDestroy() is executed
        mPresenter.dropView(this);
    }
}