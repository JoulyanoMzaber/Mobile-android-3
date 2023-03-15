package com.example.roomdemodb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {

    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name="data")
    String data;

    public Data(String subject) {
        this.data = data;
    }

    public Data(){}
}
