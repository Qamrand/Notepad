package com.example.notepad.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Note entity
 */
@AllArgsConstructor
@Data
@Entity(tableName = "note")
public class Note implements Serializable {
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
