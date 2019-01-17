package com.ranjan.materialapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.ranjan.materialapp.adapters.UsersAdapter;
import com.ranjan.materialapp.data.User;
import com.ranjan.materialapp.data.database.AppDatabase;
import com.ranjan.materialapp.viewmodel.UserViewModel;
import com.ranjan.materialapp.viewmodel.UserViewModelFactory;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RoomActivity extends AppCompatActivity implements UsersAdapter.Callback {

    TextInputLayout firstName, lastName;
    Button save;
    RecyclerView users;
    UsersAdapter adapter;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        save = findViewById(R.id.save);
        users = findViewById(R.id.users);

        UserViewModelFactory factory = new InjectorUtils().provideUserViewModelFactory(this);

        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);

        adapter = new UsersAdapter(this);
        users.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        users.setAdapter(adapter);

        viewModel.getLiveUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapter.updateUsers(users);
            }
        });
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    public void onClick(View view) {
        User user = new User();
        user.firstName = firstName.getEditText().getText().toString();
        user.lastName = lastName.getEditText().getText().toString();
        viewModel.addUser(user);
    }

    @Override
    public void deleteUser(User user) {
        viewModel.deleteItem(user);
    }
}
