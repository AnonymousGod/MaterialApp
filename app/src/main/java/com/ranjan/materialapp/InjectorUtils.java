package com.ranjan.materialapp;

import android.content.Context;

import com.ranjan.materialapp.data.UserRepository;
import com.ranjan.materialapp.data.database.AppDatabase;
import com.ranjan.materialapp.network_backed_db.OutletRepository;
import com.ranjan.materialapp.network_backed_db.OutletViewModelFactory;
import com.ranjan.materialapp.network_paging.StoreRepository;
import com.ranjan.materialapp.network_paging.StoreViewModelFactory;
import com.ranjan.materialapp.room_network_paging.LocalStoreRepository;
import com.ranjan.materialapp.room_network_paging.RoomNetworkPagingVMFactory;
import com.ranjan.materialapp.viewmodel.UserViewModelFactory;

/**
 * Created by BlueSapling on 1/16/19.
 */
public class InjectorUtils {

    private UserRepository getUserRepository(Context context) {
        return UserRepository.getInstance(AppDatabase.getDatabase(context).userDao());
    }

    private OutletRepository getOutletRepository(Context context) {
        return OutletRepository.getInstance(AppDatabase.getDatabase(context).storeDao());
    }

    private StoreRepository getStoreRepository() {
        return StoreRepository.getInstance();
    }

    public UserViewModelFactory provideUserViewModelFactory(Context context) {
        UserRepository repository = getUserRepository(context);
        return new UserViewModelFactory(repository);
    }

    public OutletViewModelFactory provideOutletViewModelFactory(Context context) {
        OutletRepository repository = getOutletRepository(context);
        return new OutletViewModelFactory(repository);
    }

    private LocalStoreRepository getLocalStoreRepository(Context context) {
        return LocalStoreRepository.getInstance(context, AppDatabase.getDatabase(context).storeDao());
    }

    public RoomNetworkPagingVMFactory provideRoomNetworkPagingFactory(Context context) {
        LocalStoreRepository repository = getLocalStoreRepository(context);
        return new RoomNetworkPagingVMFactory(repository);
    }

    StoreViewModelFactory provideStoreViewModelFactory() {
        return new StoreViewModelFactory();
    }
}
