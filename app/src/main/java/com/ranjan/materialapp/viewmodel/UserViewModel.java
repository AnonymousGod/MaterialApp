package com.ranjan.materialapp.viewmodel;

import android.os.AsyncTask;

import com.ranjan.materialapp.data.User;
import com.ranjan.materialapp.data.UserRepository;
import com.ranjan.materialapp.data.database.AppDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BlueSapling on 1/15/19.
 */
public class UserViewModel extends ViewModel {

    List<User> userList;
    UserRepository repository;
    private LiveData<List<User>> liveUsers;

    public UserViewModel(UserRepository repository) {
        this.repository = repository;
        liveUsers = repository.getAllUsers();
    }

    public LiveData<List<User>> getLiveUsers() {
        return liveUsers;
    }

    public void addUser(final User user) {
        //new insertAsyncTask(appDatabase).execute(user);
        Completable.fromAction(
                new Action() {
                    @Override
                    public void run() {
                        repository.insertUser(user);
                    }
                }
        )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe();
    }

    public void deleteItem(User user) {
        new deleteAsyncTask(repository).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private AppDatabase db;

        insertAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params) {
            db.userDao().insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void> {

        private UserRepository repository;

        deleteAsyncTask(UserRepository repository) {
            this.repository = repository;
        }

        @Override
        protected Void doInBackground(final User... params) {
            repository.deleteUser(params[0]);
            return null;
        }
    }

    public List<User> getUserList() {
        return userList;
    }
}
