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
import com.example.notepad.ui.activity.category.CategoryContract;
import com.example.notepad.ui.activity.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private List<Category> mCategoryList;
    /**
     * id isVisibleEditButtons = true - display edit buttons in recyclerview elements
     */
    private boolean isVisibleEditButtons;
    private CategoryContract.Presenter mPresenter;

    @Inject
    public CategoryAdapter(Context context, boolean isVisibleEditButtons, CategoryContract.Presenter presenter) {
        mContext = context;
        mCategoryList = new ArrayList<>();
        this.isVisibleEditButtons = isVisibleEditButtons;
        mPresenter = presenter;
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

        /*
         * Show edit buttons if isVisibleEditButtons is true.
         * Show edit buttons for all categories except categories "All notes" and "No category"
         */
        if (isVisibleEditButtons &&
                !(mCategoryList.get(position).getMName().equals("All notes")
                        || mCategoryList.get(position).getMName().equals("No category"))) {
            holder.mEditButton.setVisibility(View.VISIBLE);
            holder.mRemoveButton.setVisibility(View.VISIBLE);
        }

        clickButtons(holder, position);
    }

    /**
     * for initialize onClick method in holder elements
     * @param holder - one category
     * @param position - position in recycler view
     */
    private void clickButtons(@NonNull CategoryViewHolder holder, int position) {

        holder.mTextView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

            //put category's name in intent
            intent.putExtra(MainActivity.EXTRA_CATEGORY_NAME, mCategoryList.get(position).getMName());
            mContext.startActivity(intent);
        });

        //delete category
        holder.mRemoveButton.setOnClickListener(v -> Completable.fromAction(() ->
                mPresenter.removeCategory(mCategoryList.get(position).getMId()))
                .subscribeOn(Schedulers.io())
                .subscribe());

        //edit category
        holder.mEditButton.setOnClickListener(v ->
                mPresenter.addNewCategory(mPresenter.getCategoryActivity(), mCategoryList.get(position)));
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    /**
     * initialize List<Category>
     * @param categories - data from Room
     */
    public void setData(List<Category> categories) {
        clearData();
        mCategoryList.addAll(categories);
        //Notifies the attached observers that the underlying data has been changed
        // and any View reflecting the data set should refresh itself.
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
