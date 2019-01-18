package com.ranjan.materialapp.network_paging;

import android.util.Log;

import com.ranjan.materialapp.data.Store;
import com.ranjan.materialapp.network_backed_db.api.StoresApi;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class PagedKeyStoreDataSource extends PageKeyedDataSource<String, Store> {

    private StoresApi storesApi;

    PagedKeyStoreDataSource(StoresApi storesApi) {
        this.storesApi = storesApi;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Store> callback) {
        SingleObserver<StoreResponse> subscriber =
                new SingleObserver<StoreResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(StoreResponse list) {
                        callback.onResult(list.getStores(), null, list.getLinks().getNext());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("DataSource", "onError: " + throwable.getLocalizedMessage());
                    }
                };

        storesApi.getStores().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Store> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Store> callback) {
        SingleObserver<StoreResponse> subscriber =
                new SingleObserver<StoreResponse>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(StoreResponse list) {
                        callback.onResult(list.getStores(), list.getLinks().getNext());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("DataSource", "onError: " + throwable.getLocalizedMessage());
                    }
                };

        storesApi.getNextPageStores(params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }
}