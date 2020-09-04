package com.example.notepad.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.database.entity.Category;
import com.example.notepad.ui.activity.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHoled> {

    private Context mContext;
    private List<Category> mCategoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        mContext = context;
        mCategoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHoled onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHoled(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHoled holder, int position) {
        holder.mTextView.setText(mCategoryList.get(position).getMName());

        holder.mTextView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MainActivity.class);
            //put category's name in intent
            intent.putExtra(MainActivity.EXTRA_CATEGORY_NAME, mCategoryList.get(position).getMName());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public static class CategoryViewHoled extends RecyclerView.ViewHolder {
        @BindView(R.id.category_name)
        TextView mTextView;

        public CategoryViewHoled(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
