package com.ranjan.materialapp.data;

import androidx.room.ColumnInfo;

/**
 * Created by BlueSapling on 1/15/19.
 */
public class NameTuple {
    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}