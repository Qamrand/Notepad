package com.example.notepad.data;

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
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "text")
    private String mText;
    @ColumnInfo(name = "favourite")
    private int isFavourite;
    @ColumnInfo(name = "category")
    private String mCategory;
    @ColumnInfo(name = "creating")
    private String mCreatingDate;
    @ColumnInfo(name = "modify")
    private String mModifyDate;

}
