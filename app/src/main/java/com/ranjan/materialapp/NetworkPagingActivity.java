package com.ranjan.materialapp;

import android.os.Bundle;
import android.view.MenuItem;

import com.ranjan.materialapp.network_paging.StoreAdapter;
import com.ranjan.materialapp.network_paging.StoreRepository;
import com.ranjan.materialapp.network_paging.StoreViewModelFactory;
import com.ranjan.materialapp.network_paging.StoresViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NetworkPagingActivity extends AppCompatActivity {

    private StoresViewModel storesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_paging);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        StoreViewModelFactory factory = new InjectorUtils().provideStoreViewModelFactory();

        storesViewModel = ViewModelProviders.of(this, factory).get(StoresViewModel.class);

        RecyclerView rv = findViewById(R.id.store_list);
        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        StoreAdapter adapter = new StoreAdapter();
        rv.setAdapter(adapter);
        storesViewModel.init(StoreRepository.getInstance());
        storesViewModel.getArticleLiveData().observe(this, adapter::submitList);

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

}
