package com.ranjan.materialapp.network_backed_db.api;

import com.ranjan.materialapp.network_paging.StoreResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by BlueSapling on 1/17/19.
 */
public interface StoresApi {
    @GET("/stores")
    Single<StoreResponse> getStores();

    @GET
    Single<StoreResponse> getNextPageStores(@Url String url);
}