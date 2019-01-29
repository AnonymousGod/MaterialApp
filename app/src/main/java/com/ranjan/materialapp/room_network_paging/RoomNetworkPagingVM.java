package com.ranjan.materialapp.room_network_paging;

import com.ranjan.materialapp.data.Store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

/**
 * Created by BlueSapling on 1/22/19.
 */
public class RoomNetworkPagingVM extends ViewModel {

    private LocalStoreRepository repository;
    private LiveData<PagedList<Store>> storeLiveData;

    public RoomNetworkPagingVM(LocalStoreRepository repository) {
        this.repository = repository;
        storeLiveData = repository.storesFromDB();
    }

    public LiveData<PagedList<Store>> getStoresFromDB() {
        return storeLiveData;
    }
}
