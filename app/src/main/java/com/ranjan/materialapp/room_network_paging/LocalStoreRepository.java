package com.ranjan.materialapp.room_network_paging;

import android.content.Context;

import com.ranjan.materialapp.data.Store;
import com.ranjan.materialapp.data.StoreDao;
import com.ranjan.materialapp.network_backed_db.api.StoresApi;
import com.ranjan.materialapp.network_backed_db.api.StoresApiFactory;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * Created by BlueSapling on 1/22/19.
 */
public class LocalStoreRepository {

    private Context context;
    private StoresApi restApi;
    private StoreDao storeDao;
    private static LocalStoreRepository INSTANCE;

    public static LocalStoreRepository getInstance(Context context, StoreDao storeDao) {
        if (INSTANCE == null) {
            synchronized (LocalStoreRepository.class) {
                INSTANCE = new LocalStoreRepository(context, storeDao);
            }
        }
        return INSTANCE;
    }

    private LocalStoreRepository(Context context, StoreDao storeDao) {
        this.context = context;
        this.storeDao = storeDao;
        this.restApi = StoresApiFactory.create();
    }

    @MainThread
    LiveData<PagedList<Store>> storesFromDB() {

        DataSource.Factory<Integer, Store> factory = storeDao.getStoresFromDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .build();

        LivePagedListBuilder<Integer, Store> pagedListBuilder
                = new LivePagedListBuilder<>(factory, config)
                .setBoundaryCallback(new BoundaryCallback(context, restApi, storeDao));

        return pagedListBuilder.build();
    }
}
