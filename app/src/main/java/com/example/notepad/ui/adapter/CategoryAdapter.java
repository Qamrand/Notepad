package com.example.notepad.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.database.entity.Category;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.activity.category.CategoryComponent;
import com.example.notepad.ui.activity.category.CategoryContract;
import com.example.notepad.ui.activity.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private List<Category> mCategoryList;
    private boolean isVisibleEditButtons;

    @Inject
    CategoryContract.Presenter mPresenter;

    @Inject
    public CategoryAdapter(Context context, boolean isVisibleEditButtons) {
        mContext = context;
        mCategoryList = new ArrayList<>();
        this.isVisibleEditButtons = isVisibleEditButtons;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.mTextView.setText(mCategoryList.get(position).getMName());
        if(isVisibleEditButtons) {
            holder.mEditButton.setVisibility(View.VISIBLE);
            holder.mRemoveButton.setVisibility(View.VISIBLE);
        }

        holder.mTextView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MainActivity.class);
            //put category's name in intent
            intent.putExtra(MainActivity.EXTRA_CATEGORY_NAME, mCategoryList.get(position).getMName());
            mContext.startActivity(intent);
        });

        holder.mRemoveButton.setOnClickListener(v -> {
            mPresenter.removeCategory(mCategoryList.get(position));
        });

        holder.mEditButton.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public void setData(List<Category> categories) {
        clearData();
        mCategoryList.addAll(categories);
        notifyDataSetChanged();
    }

    public void clearData() {
        mCategoryList.clear();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_name)
        TextView mTextView;

        @BindView(R.id.category_edit_image_button)
        ImageButton mEditButton;

        @BindView(R.id.category_remove_image_button)
        ImageButton mRemoveButton;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
