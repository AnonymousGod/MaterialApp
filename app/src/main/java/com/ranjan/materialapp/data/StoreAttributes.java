package com.ranjan.materialapp.data;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;

/**
 * Created by BlueSapling on 1/18/19.
 */
public class StoreAttributes {

    @ColumnInfo(name = "title")
    @SerializedName("title")
    public String title;

    @ColumnInfo(name = "location")
    @SerializedName("location")
    public String location;
}
