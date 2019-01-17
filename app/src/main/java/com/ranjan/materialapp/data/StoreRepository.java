package com.ranjan.materialapp.data;

import com.ranjan.materialapp.data.database.AppDatabase;
import com.ranjan.materialapp.network_backed_db.api.StoresApiFactory;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class StoreRepository {
    public AppDatabase database;
    private StoresApiFactory apiFactory;
    private Executor ioExecutor;
    int networkPageSize = 10;

    public StoreRepository(AppDatabase database, StoresApiFactory apiFactory, Executor executor) {
        this.database = database;
        this.apiFactory = apiFactory;
        this.ioExecutor = executor;
    }

    private void insertResultIntoDB(List<Store> stores) {
        //Perform operation on Store item to have title and address extracted from attributes
        database.storeDao().insertAll(stores);
    }

//    /**
//     * Returns a Listing for the given stores.
//     */
//    @MainThread
//    public LiveData<PagedList<Store>> allTheStores(int pageSize) {
//        StoreBoundaryCallback boundaryCallback = new StoreBoundaryCallback();
//
//        // We use toLiveData Kotlin extension function here, you could also use LivePagedListBuilder
//        LiveData<PagedList<Store>> livePagedList = database.storeDao().GetAllStoreLive();
//
//        return livePagedList;
//    }
}


