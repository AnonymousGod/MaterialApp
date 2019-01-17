package com.ranjan.materialapp.network_backed_db;

import com.ranjan.materialapp.data.Store;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class OutletViewModel extends ViewModel {

    private OutletRepository repository;
    private LiveData<List<Store>> liveStores;

    OutletViewModel(OutletRepository repository) {
        this.repository = repository;
        liveStores = repository.getAllStores();
    }

    public LiveData<List<Store>> getLiveStores() {
        return liveStores;
    }

    public void changeType() {
        repository.changeType();
    }
}
