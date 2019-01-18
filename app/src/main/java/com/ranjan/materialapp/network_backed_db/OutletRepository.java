package com.ranjan.materialapp.network_backed_db;

import android.os.AsyncTask;
import android.util.Log;

import com.ranjan.materialapp.data.Store;
import com.ranjan.materialapp.data.StoreDao;
import com.ranjan.materialapp.network_backed_db.api.StoresApi;
import com.ranjan.materialapp.network_backed_db.api.StoresApiFactory;
import com.ranjan.materialapp.network_paging.StoreResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class OutletRepository {
    private static OutletRepository INSTANCE;

    private StoresApi restApi;

    public static OutletRepository getInstance(StoreDao storeDao) {
        if (INSTANCE == null) {
            synchronized (OutletRepository.class) {
                INSTANCE = new OutletRepository(storeDao);
            }
        }
        return INSTANCE;
    }

    private StoreDao storeDao;

    private OutletRepository(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    private void insertStoresIntoDB(StoreResponse response) {
        new insertAsyncTask(storeDao).execute(response.getStores());
    }

    private static class insertAsyncTask extends AsyncTask<List<Store>, Void, Void> {

        private StoreDao storeDao;

        insertAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(final List<Store>... params) {
            storeDao.insertAll(params[0]);
            return null;
        }
    }

    void changeType() {
        new updateAsyncTask(storeDao).execute();
    }

    private static class updateAsyncTask extends AsyncTask<Void, Void, Void> {

        private StoreDao storeDao;

        updateAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            storeDao.changeType(270);
            return null;
        }
    }

    LiveData<List<Store>> getAllStores() {
        //Fetch from network
        this.restApi = StoresApiFactory.create();

        SingleObserver<StoreResponse> subscriber =
                new SingleObserver<StoreResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(StoreResponse list) {
                        Log.d("OutletRepository", "onSuccess: " + list);
                        insertStoresIntoDB(list);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("OutletRepository", "onError: " + throwable.getLocalizedMessage());
                    }
                };

        restApi.getStores().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);

        return storeDao.getAllStores();
    }

}
