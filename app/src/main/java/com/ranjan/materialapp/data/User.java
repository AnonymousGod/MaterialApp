package com.ranjan.materialapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Created by BlueSapling on 1/15/19.
 */
@Entity(tableName = "users",
        indices = {@Index(value = "first_name", unique = true),
                @Index(value = {"last_name", "street"})})
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @Embedded
    Address address;
}