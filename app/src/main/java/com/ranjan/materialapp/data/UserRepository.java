package com.ranjan.materialapp.data;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by BlueSapling on 1/16/19.
 */
public class UserRepository {

    private static UserRepository INSTANCE;

    public static UserRepository getInstance(UserDao userDao) {
        if (INSTANCE == null) {
            synchronized (UserRepository.class) {
                INSTANCE = new UserRepository(userDao);
            }
        }
        return INSTANCE;
    }

    private UserDao userDao;

    private UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public LiveData<List<User>> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void insertUser(User user) {
        userDao.insert(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

}