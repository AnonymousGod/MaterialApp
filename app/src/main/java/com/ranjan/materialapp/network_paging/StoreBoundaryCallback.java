package com.ranjan.materialapp.network_paging;

import com.ranjan.materialapp.data.Store;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class StoreBoundaryCallback extends PagedList.BoundaryCallback<Store> {
    private String mQuery;
//    private MyService mService;
//    private MyLocalCache mCache;

    public StoreBoundaryCallback() {

    }

    // Requests initial data from the network, replacing all content currently
    // in the database.
    @Override
    public void onZeroItemsLoaded() {
//        requestAndReplaceInitialData(mQuery);
    }

    // Requests additional data from the network, appending the results to the
    // end of the database's existing data.
    @Override
    public void onItemAtEndLoaded(@NonNull Store itemAtEnd) {
//        requestAndAppendData(mQuery, itemAtEnd.key);
    }
}