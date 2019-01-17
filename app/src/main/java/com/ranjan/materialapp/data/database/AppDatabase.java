package com.ranjan.materialapp.data.database;

import android.content.Context;

import com.ranjan.materialapp.data.Converters;
import com.ranjan.materialapp.data.Store;
import com.ranjan.materialapp.data.StoreDao;
import com.ranjan.materialapp.data.User;
import com.ranjan.materialapp.data.UserDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Created by BlueSapling on 1/15/19.
 */
@Database(entities = {User.class, Store.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "room_db")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract UserDao userDao();

    public abstract StoreDao storeDao();
}