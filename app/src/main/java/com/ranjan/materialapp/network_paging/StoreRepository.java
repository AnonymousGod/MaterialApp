package com.ranjan.materialapp.network_paging;

import com.ranjan.materialapp.data.Store;
import com.ranjan.materialapp.network_backed_db.api.StoresApi;
import com.ranjan.materialapp.network_backed_db.api.StoresApiFactory;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class StoreRepository {
    private static StoreRepository INSTANCE;

    public static StoreRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (StoreRepository.class) {
                INSTANCE = new StoreRepository();
            }
        }
        return INSTANCE;
    }

    LiveData<PagedList<Store>> getAllStores() {
        StoresApi restApi = StoresApiFactory.create();

        //Create a DataSource factory
        PagedKeyStoreDataSourceFactory sourceFactory = new PagedKeyStoreDataSourceFactory(restApi);

        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(40)
                .build();

        return new LivePagedListBuilder<>(sourceFactory, 20).build();
    }
}
