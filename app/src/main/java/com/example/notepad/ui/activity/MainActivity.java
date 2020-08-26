package com.example.notepad.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.notepad.R;
import com.example.notepad.ui.fragment.AddNoteFragment;
import com.example.notepad.ui.fragment.CategoryFragment;
import com.example.notepad.ui.fragment.FavouriteFragment;
import com.example.notepad.ui.fragment.NoteListFragment;
import com.example.notepad.ui.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.note_list_fragment)
    FrameLayout mNoteListFragment;
    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_bottom_categories:
                    mNoteListFragment.setVisibility(View.GONE);
                    updateFragment(new CategoryFragment(), R.id.menu_bottom_categories);
                    break;
                case R.id.menu_bottom_search:
                    mNoteListFragment.setVisibility(View.GONE);
                    updateFragment(new SearchFragment(), R.id.menu_bottom_search);
                    break;
                case R.id.menu_bottom_add:
                    mNoteListFragment.setVisibility(View.GONE);
                    updateFragment(new AddNoteFragment(), R.id.menu_bottom_add);
                    break;
                case R.id.menu_bottom_favorite:
                    mNoteListFragment.setVisibility(View.GONE);
                    updateFragment(new FavouriteFragment(), R.id.menu_bottom_favorite);
                    break;
            }
            return true;
        });
    }

    private void updateFragment(Fragment fragment, int fragmentId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        System.out.println("---------------------------" + fragmentManager.getFragments().size());
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (fragmentManager.getFragments().isEmpty()) {
            ft.add(R.id.main_fragment, new NoteListFragment());
            ft.addToBackStack(null);
        }

        ft.replace(R.id.main_fragment, fragment);
        ft.addToBackStack(null);

        ft.commit();
    }
}