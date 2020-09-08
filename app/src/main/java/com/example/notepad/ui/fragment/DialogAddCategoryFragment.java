package com.example.notepad.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.notepad.MyApplication;
import com.example.notepad.R;
import com.example.notepad.database.entity.Category;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class DialogAddCategoryFragment extends DialogFragment {

    @BindView(R.id.category_name_dialog)
    public EditText nameCategory;

    private Category mCategory = new Category();

    public void setCategory(Category category) {
        this.mCategory = category;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireActivity())
                .inflate(R.layout.dialog_add_category, null);

        ButterKnife.bind(this, view);
        nameCategory.setText(mCategory.getMName());
        return new MaterialAlertDialogBuilder(requireActivity())
                .setView(view)
                .setTitle(R.string.add_category_title)
                .setPositiveButton(R.string.dialog_category_add,
                        ((dialogInterface, i) -> {
                            String name = nameCategory.getText().toString();
                            if(!name.isEmpty()) {
                                mCategory.setMName(name);
                                Completable.fromAction(() -> MyApplication.categoryRepository.insertCategory(mCategory))
                                        .subscribeOn(Schedulers.io())
                                        .subscribe();
                            } else {
                                Toast.makeText(this.getContext(), "Category name is empty", Toast.LENGTH_SHORT).show();
                            }
                        }))
                .create();
    }
}
