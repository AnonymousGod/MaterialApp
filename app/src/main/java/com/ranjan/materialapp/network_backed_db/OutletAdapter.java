package com.ranjan.materialapp.network_backed_db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ranjan.materialapp.R;
import com.ranjan.materialapp.data.Store;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by BlueSapling on 1/17/19.
 */
public class OutletAdapter extends RecyclerView.Adapter<OutletAdapter.OutletHolder> {

    private List<Store> storeList;

    public OutletAdapter() {
        storeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public OutletHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.outlet_info, parent, false);
        return new OutletHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OutletHolder holder, int position) {
        holder.storeID.setText(String.valueOf(storeList.get(position).id));
        holder.storeType.setText(storeList.get(position).type);
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public void updateStores(List<Store> storeList) {
        this.storeList = storeList;
        notifyDataSetChanged();
    }

    class OutletHolder extends RecyclerView.ViewHolder {
        TextView storeID, storeType;

        OutletHolder(@NonNull View itemView) {
            super(itemView);

            storeID = itemView.findViewById(R.id.item_store_id);
            storeType = itemView.findViewById(R.id.item_store_type);
        }
    }
}
