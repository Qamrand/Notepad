package com.example.notepad.ui.activity.addnote;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.notepad.MyApplication;
import com.example.notepad.R;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNoteActivity extends AppCompatActivity implements AddNoteContract.View {

    public static final String EXTRA_CATEGORY_NAME = "category_name";
    public static final String EXTRA_NOTE_EDIT_PARAMETERS_ID = "edit_note_id";
    public static final String EXTRA_NOTE_EDIT_PARAMETERS = "edit_note";

    private static final String TAG = "AddNoteActivity";
    private AddNoteComponent mAddNoteComponent;

    @BindView(R.id.toolbar_edit)
    Toolbar mToolbar;

    @BindView(R.id.edit_text_toolbar)
    EditText mNoteNameText;

    @BindView(R.id.edit_text_note_content)
    EditText mNoteContent;

    @BindView(R.id.save_button)
    Button mSaveButton;

    @Inject
    AddNoteContract.Presenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);

        String[] note = Objects.requireNonNull(getIntent().getExtras()).getStringArray(EXTRA_NOTE_EDIT_PARAMETERS);
        if(note != null) {
            //Log.d(TAG, "in bundle = " + note);
            mNoteNameText.setText(note[0]);
            mNoteContent.setText(note[1]);

        }
        mAddNoteComponent = MyApplication.appComponent
                .addNoteComponent()
                .build();

        mAddNoteComponent.inject(this);

        mPresenter.setView(this);

        mToolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mSaveButton.setOnClickListener(view -> {
            mPresenter.onSaveClick(this, getIntent());
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
        /*String category = getIntent().getExtras().getString(EXTRA_CATEGORY_NAME);
        if(category.equals("All notes") || category.equals("No category"))
            category = "";
        return category;*/
        return Objects.requireNonNull(getIntent().getExtras()).getString(EXTRA_CATEGORY_NAME);
    }

}