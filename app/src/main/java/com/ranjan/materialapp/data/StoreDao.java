package com.ranjan.materialapp.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by BlueSapling on 1/17/19.
 */
@Dao
public interface StoreDao extends BaseDao<Store> {
    @Query("SELECT * FROM stores")
    LiveData<List<Store>> getAllStores();

    @Query("SELECT * FROM stores")
    LiveData<List<Store>> GetAllStoreLive();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Store> stores);

    @Query("UPDATE stores SET type = 'shopping center', title = 'Phoenix Mall', location='Bangalore' WHERE id = :id")
    void changeType(int id);

}
