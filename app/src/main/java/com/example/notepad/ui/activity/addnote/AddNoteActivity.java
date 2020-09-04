package com.example.notepad.ui.activity.addnote;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.notepad.MyApplication;
import com.example.notepad.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNoteActivity extends AppCompatActivity implements AddNoteContract.View {

    public static final String EXTRA_CATEGORY_NAME = "category_name";

    @BindView(R.id.toolbar_edit)
    Toolbar mToolbar;

    @BindView(R.id.edit_text_toolbar)
    EditText mNoteNameText;

    @BindView(R.id.edit_text_note_content)
    EditText mNoteContent;

    @BindView(R.id.save_button)
    Button mSaveButton;

    private AddNoteComponent mAddNoteComponent;

    @Inject
    AddNoteContract.Presenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        mAddNoteComponent = MyApplication.appComponent
                .addNoteComponent()
                .build();

        mAddNoteComponent.inject(this);

        mPresenter.setView(this);

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mSaveButton.setOnClickListener(view -> {
            mPresenter.onSaveClick(this);
            mPresenter.dropView(this);
        });
    }

    @Override
    public String getName() {
        return mNoteNameText.getText().toString();
    }

    @Override
    public String getDescriptionText() {
        return mNoteContent.getText().toString();
    }

    @Override
    public String getCategory() {
        return getIntent().getExtras().getString(EXTRA_CATEGORY_NAME);
    }

}