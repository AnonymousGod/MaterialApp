package com.ranjan.materialapp;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ranjan.materialapp.network_backed_db.OutletAdapter;
import com.ranjan.materialapp.network_backed_db.OutletViewModel;
import com.ranjan.materialapp.network_backed_db.OutletViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RoomNetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_network);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        OutletViewModelFactory factory = new InjectorUtils().provideOutletViewModelFactory(this);

        OutletViewModel viewModel =
                ViewModelProviders.of(this, factory).get(OutletViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.store_list);
        FloatingActionButton changeType = findViewById(R.id.change_type);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        OutletAdapter adapter = new OutletAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getLiveStores().observe(this, adapter::updateStores);

        changeType.setOnClickListener(v -> viewModel.changeType());
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
