package com.example.notepad.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.MyApplication;
import com.example.notepad.R;
import com.example.notepad.database.entity.Note;
import com.example.notepad.ui.activity.notepage.NotePageActivity;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

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

    /**
     * initialize category holder elements
     */
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (mNoteList.get(position).getIsFavourite() == 0)
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
            Note note = mNoteList.get(position);
            if (note.getIsFavourite() == 0) {
                holder.mImageFavouriteItem.setImageResource(R.drawable.ic_favourite_note_full);
                note.setIsFavourite(1);
            } else {
                holder.mImageFavouriteItem.setImageResource(R.drawable.ic_favourite_note_border);
                note.setIsFavourite(0);
            }

            Completable.fromAction(() -> MyApplication.noteRepository
                    .updateNote(note))
                    .subscribeOn(Schedulers.io())
                    .subscribe();
        });
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    /**
     * initialize List<Note>
     * @param notes - data from Room
     */
    public void setData(List<Note> notes) {
        clearData();
        mNoteList.addAll(notes);
        //Notifies the attached observers that the underlying data has been changed
        // and any View reflecting the data set should refresh itself.
        notifyDataSetChanged();
    }

    public void clearData() {
        mNoteList.clear();
    }

    /**
     * Set LayoutManager in @param context
     * @return GridLayoutManager depending on screen orientation
     */
    public GridLayoutManager setGridLayoutManager(Context context) {
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
