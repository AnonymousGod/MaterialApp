package com.ranjan.materialapp.network_paging;

import com.google.gson.annotations.SerializedName;
import com.ranjan.materialapp.data.Links;
import com.ranjan.materialapp.data.Store;

import java.util.List;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class StoreResponse {

    @SerializedName("data")
    public List<Store> stores;

    @SerializedName("links")
    public Links links;

    public List<Store> getStores() {
        return stores;
    }

    public Links getLinks() {
        return links;
    }
}
