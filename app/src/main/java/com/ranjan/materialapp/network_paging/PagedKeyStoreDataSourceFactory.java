package com.ranjan.materialapp.network_paging;

import com.ranjan.materialapp.data.Store;
import com.ranjan.materialapp.network_backed_db.api.StoresApi;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

/**
 * Created by BlueSapling on 1/18/19.
 */
public class PagedKeyStoreDataSourceFactory extends DataSource.Factory<String, Store> {

    private StoresApi storesApi;

    PagedKeyStoreDataSourceFactory(StoresApi storesApi) {
        this.storesApi = storesApi;
    }

    @NonNull
    @Override
    public DataSource<String, Store> create() {
        return new PagedKeyStoreDataSource(storesApi);
    }
}