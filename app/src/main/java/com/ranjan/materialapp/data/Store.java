package com.ranjan.materialapp.data;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by BlueSapling on 1/17/19.
 */
@Entity(tableName = "stores")
public class Store {
    @PrimaryKey
    @SerializedName("id")
    public int id;

    @ColumnInfo(name = "type")
    @SerializedName("type")
    public String type;

    @SerializedName("attributes")
    @Embedded
    public StoreAttributes attributes;
}
