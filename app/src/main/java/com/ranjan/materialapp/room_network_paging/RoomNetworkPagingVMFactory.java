package com.ranjan.materialapp.room_network_paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by BlueSapling on 1/22/19.
 */
public class RoomNetworkPagingVMFactory implements ViewModelProvider.Factory {
    private LocalStoreRepository repo;

    public RoomNetworkPagingVMFactory(LocalStoreRepository repository) {
        repo = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RoomNetworkPagingVM(repo);
    }
}
