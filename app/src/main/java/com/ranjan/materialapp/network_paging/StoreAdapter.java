package com.ranjan.materialapp.network_paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ranjan.materialapp.R;
import com.ranjan.materialapp.data.Store;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class StoreAdapter extends PagedListAdapter<Store, StoreAdapter.StoreViewHolder> {

    public StoreAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.outlet_info, parent, false);
        StoreViewHolder viewHolder = new StoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder,
                                 int position) {
        holder.storeID.setText(String.valueOf(getItem(position).id));
        holder.storeType.setText(getItem(position).type);
        holder.storeTitle.setText(getItem(position).attributes.title);
        holder.storeLocation.setText(getItem(position).attributes.location);
    }

    private static DiffUtil.ItemCallback<Store> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Store>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Store oldStore, Store newStore) {
                    return oldStore.id == newStore.id;
                }

                @Override
                public boolean areContentsTheSame(Store oldStore,
                                                  Store newStore) {
                    return oldStore.equals(newStore);
                }
            };

    class StoreViewHolder extends RecyclerView.ViewHolder {

        TextView storeID, storeType, storeTitle, storeLocation;

        StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            storeID = itemView.findViewById(R.id.item_store_id);
            storeType = itemView.findViewById(R.id.item_store_type);
            storeTitle = itemView.findViewById(R.id.item_store_title);
            storeLocation = itemView.findViewById(R.id.item_store_location);
        }
    }
}
