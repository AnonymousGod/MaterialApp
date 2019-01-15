package com.ranjan.materialapp.data;

import androidx.room.ColumnInfo;

/**
 * Created by BlueSapling on 1/15/19.
 */
public class Address {
    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;
}
