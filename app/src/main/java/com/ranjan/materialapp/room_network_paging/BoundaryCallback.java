package com.ranjan.materialapp.room_network_paging;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ranjan.materialapp.SharedPrefUtils;
import com.ranjan.materialapp.data.Store;
import com.ranjan.materialapp.data.StoreDao;
import com.ranjan.materialapp.network_backed_db.api.StoresApi;
import com.ranjan.materialapp.network_paging.StoreResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BlueSapling on 1/22/19.
 */
public class BoundaryCallback extends PagedList.BoundaryCallback<Store> {
    private StoresApi storesApi;
    private StoreDao mStoreDao;
    private String nextUrl;
    private Context context;
    private boolean fetching;
    private String TAG = "BoundaryCallback";

    BoundaryCallback(Context context, StoresApi restApi, StoreDao storeDao) {
        this.context = context;
        storesApi = restApi;
        mStoreDao = storeDao;
        nextUrl = SharedPrefUtils.getString(context, "next_url");
    }

    private void insertItemsIntoDb(StoreResponse response) {
        new insertAsyncTask(mStoreDao).execute(response.getStores());
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

    // Requests initial data from the network, replacing all content currently
    // in the database.
    @Override
    public void onZeroItemsLoaded() {
        SingleObserver<StoreResponse> subscriber =
                new SingleObserver<StoreResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(StoreResponse list) {
                        insertItemsIntoDb(list);
                        Log.d(TAG, "onZeroItemsLoaded: first time stores response");
                        nextUrl = list.getLinks().getNext();
                        SharedPrefUtils.addString(context, "next_url", nextUrl);
                        fetching = false;
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                };

        if (!fetching) {
            Log.d(TAG, "onZeroItemsLoaded: first time getting stores");
            fetching = true;
            storesApi.getStores().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        }
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull Store itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
        Log.d(TAG, "onItemAtFrontLoaded: called");
    }

    // Requests additional data from the network, appending the results to the
    // end of the database's existing data.
    @Override
    public void onItemAtEndLoaded(@NonNull Store itemAtEnd) {
        if (nextUrl != null) {
            requestAndAppendData(nextUrl);
        }
    }

    private void requestAndAppendData(String url) {

        SingleObserver<StoreResponse> subscriber =
                new SingleObserver<StoreResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(StoreResponse list) {
                        Log.d(TAG, "requestAndAppendData: next page stores url " + url);
                        insertItemsIntoDb(list);
                        nextUrl = list.getLinks().getNext();
                        SharedPrefUtils.addString(context, "next_url", nextUrl);
                        fetching = false;
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }
                };

        if (!fetching) {
            Log.d(TAG, "requestAndAppendData: calling next page stores url " + url);
            fetching = true;
            storesApi.getNextPageStores(url).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
        }
    }
}