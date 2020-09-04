package com.example.notepad.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.notepad.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogAddCategoryFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(requireActivity())
                .inflate(R.layout.dialog_add_category, null);

        EditText nameCategory = view.findViewById(R.id.category_name);

        return new MaterialAlertDialogBuilder(requireActivity())
                .setView(view)
                .setTitle(R.string.add_category_title)
                .setPositiveButton(R.string.dialog_category_add,
                        ((dialogInterface, i) -> {

                        }))
                .create();
    }
}
