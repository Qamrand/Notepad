package com.example.notepad.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.R;
import com.example.notepad.data.Note;
import com.example.notepad.ui.activity.NotePageActivity;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private Context mContext;
    private List<Note> mNoteList;

    public NoteListAdapter(Context context, List<Note> noteList) {
        mContext = context;
        mNoteList = noteList;
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
        holder.mImageFavouriteItem.setImageResource(mNoteList.get(position).getIsFavourite());
        holder.mTextCategoryItem.setText(mNoteList.get(position).getMCategory());
        holder.mTextNameItem.setText(mNoteList.get(position).getMName());

        holder.mTextNameItem.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, NotePageActivity.class);
            intent.putExtra(NotePageActivity.EXTRA_NOTE_ID, position);
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
