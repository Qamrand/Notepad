package com.example.notepad.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(tableName = "category")
public class Category {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;
    @ColumnInfo(name = "name")
    private String mName;

}
