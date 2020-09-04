package com.example.notepad.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor()
@Data
@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "text")
    private String mText;

    @ColumnInfo(name = "favourite")
    private int isFavourite = 0;

    @ColumnInfo(name = "category")
    private String mCategory;

    @ColumnInfo(name = "creating")
    private String mCreatingDate;

    @ColumnInfo(name = "modify")
    private String mModifyDate;

    public Note(String name, String text, String category, String creatingDate, String modifyDate) {
        mName = name;
        mText = text;
        mCategory = category;
        mCreatingDate = creatingDate;
        mModifyDate = modifyDate;
    }
}
