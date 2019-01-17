package com.ranjan.materialapp.data;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

/**
 * Created by BlueSapling on 1/16/19.
 */
public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T t);

    @Update
    void updateUsers(T... t);

    @Delete
    void delete(T t);
}
