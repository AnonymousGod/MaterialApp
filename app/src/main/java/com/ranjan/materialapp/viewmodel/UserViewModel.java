package com.ranjan.materialapp.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import com.ranjan.materialapp.data.User;
import com.ranjan.materialapp.data.database.AppDatabase;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BlueSapling on 1/15/19.
 */
public class UserViewModel extends AndroidViewModel {

    List<User> userList;
    AppDatabase appDatabase;
    private LiveData<List<User>> liveUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        liveUsers = appDatabase.userDao().getAllUsers();
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
                        appDatabase.userDao().insertAll(user);
                    }
                }
        )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe();
    }

    public void deleteItem(User user) {
        new deleteAsyncTask(appDatabase).execute(user);
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

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params) {
            db.userDao().delete(params[0]);
            return null;
        }
    }

    public List<User> getUserList() {
        return userList;
    }
}
