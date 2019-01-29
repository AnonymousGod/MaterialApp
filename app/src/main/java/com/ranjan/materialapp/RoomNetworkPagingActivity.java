package com.ranjan.materialapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.ranjan.materialapp.data.Store;
import com.ranjan.materialapp.databinding.ActivityRoomNetworkPagingBinding;
import com.ranjan.materialapp.room_network_paging.LocalStoreAdapter;
import com.ranjan.materialapp.room_network_paging.RoomNetworkPagingVM;
import com.ranjan.materialapp.room_network_paging.RoomNetworkPagingVMFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RoomNetworkPagingActivity extends AppCompatActivity {

    private String TAG = "RNPA";
    private RoomNetworkPagingVM viewModel;
    private ActivityRoomNetworkPagingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_network_paging);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RoomNetworkPagingVMFactory factory = new InjectorUtils().provideRoomNetworkPagingFactory(this);
        viewModel = ViewModelProviders.of(this, factory).get(RoomNetworkPagingVM.class);

        binding.storeList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        LocalStoreAdapter adapter = new LocalStoreAdapter(DIFF_CALLBACK);
        binding.storeList.setAdapter(adapter);

        binding.storeList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView.getAdapter() != null) {
                    Log.d(TAG, "onScrollStateChanged: item count " + recyclerView.getAdapter().getItemCount());
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        viewModel.getStoresFromDB().observe(this, adapter::submitList);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return true;
        }
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

}
