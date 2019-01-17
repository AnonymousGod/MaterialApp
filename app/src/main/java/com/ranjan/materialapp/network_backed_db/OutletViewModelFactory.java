package com.ranjan.materialapp.network_backed_db;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class OutletViewModelFactory implements ViewModelProvider.Factory {
    private OutletRepository repository;

    public OutletViewModelFactory(OutletRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OutletViewModel(repository);
    }
}
