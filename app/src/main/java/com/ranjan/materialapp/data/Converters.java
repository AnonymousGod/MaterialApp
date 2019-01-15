package com.ranjan.materialapp.data;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * Created by BlueSapling on 1/15/19.
 */
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}