package com.ranjan.materialapp.network_paging;

import com.ranjan.materialapp.data.Store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class StoresViewModel extends ViewModel {

    private LiveData<PagedList<Store>> storeLiveData;

    public void init(StoreRepository storeRepository) {
        storeLiveData = storeRepository.getAllStores();
    }

    public LiveData<PagedList<Store>> getArticleLiveData() {
        return storeLiveData;
    }
}