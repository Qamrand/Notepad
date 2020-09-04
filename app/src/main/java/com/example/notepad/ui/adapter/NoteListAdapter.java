package com.example.notepad.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.activity.notepage.NotePageActivity;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private Context mContext;
    private List<Note> mNoteList;

    @Inject
    public NoteListAdapter(Context context) {
        mContext = context;
        mNoteList = new ArrayList<>();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if(mNoteList.get(position).getIsFavourite() == 0)
            holder.mImageFavouriteItem.setImageResource(R.drawable.ic_favourite_note_border);
        else
            holder.mImageFavouriteItem.setImageResource(R.drawable.ic_favourite_note_full);

        holder.mTextCategoryItem.setText(mNoteList.get(position).getMCategory());
        holder.mTextNameItem.setText(mNoteList.get(position).getMName());

        holder.mTextNameItem.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, NotePageActivity.class);
            intent.putExtra(NotePageActivity.EXTRA_NOTE_ID, mNoteList.get(position).getMId());
            mContext.startActivity(intent);
        });


        holder.mImageFavouriteItem.setOnClickListener(view -> {
            if (mNoteList.get(position).getIsFavourite() == R.drawable.ic_favourite_note_border) {
                holder.mImageFavouriteItem.setImageResource(R.drawable.ic_favourite_note_full);
                mNoteList.get(position).setIsFavourite(R.drawable.ic_favourite_note_full);
            } else {
                holder.mImageFavouriteItem.setImageResource(R.drawable.ic_favourite_note_border);
                mNoteList.get(position).setIsFavourite(R.drawable.ic_favourite_note_border);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public void setData(List<Note> notes) {
        clearData();
        mNoteList.addAll(notes);
        notifyDataSetChanged();
    }

    public void clearData() {
        mNoteList.clear();
    }

    public GridLayoutManager setGridLayoutManager(Context context){
        GridLayoutManager gridLayoutManager;
        //if the orientation is portrait - draw two columns in the GridLayoutManager
        //if the orientation is landscape - three columns
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            gridLayoutManager = new GridLayoutManager(context, 2);
        else
            gridLayoutManager = new GridLayoutManager(context, 4);
        return gridLayoutManager;
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_note)
        CardView mCardView;
        @BindView(R.id.note_favorite_item)
        ImageView mImageFavouriteItem;
        @BindView(R.id.note_category_item)
        TextView mTextCategoryItem;
        @BindView(R.id.note_name_item)
        TextView mTextNameItem;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
