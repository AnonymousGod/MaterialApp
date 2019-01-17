package com.ranjan.materialapp;

import android.content.Context;

import com.ranjan.materialapp.data.UserRepository;
import com.ranjan.materialapp.data.database.AppDatabase;
import com.ranjan.materialapp.viewmodel.UserViewModelFactory;

/**
 * Created by BlueSapling on 1/16/19.
 */
public class InjectorUtils {

    private UserRepository getUserRepository(Context context) {
        return UserRepository.getInstance(AppDatabase.getDatabase(context).userDao());
    }

    public UserViewModelFactory provideUserViewModelFactory(Context context) {
        UserRepository repository = getUserRepository(context);
        return new UserViewModelFactory(repository);
    }
}
