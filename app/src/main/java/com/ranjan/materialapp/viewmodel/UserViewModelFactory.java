package com.ranjan.materialapp.viewmodel;

import com.ranjan.materialapp.data.UserRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by BlueSapling on 1/16/19.
 */
public class UserViewModelFactory implements ViewModelProvider.Factory {
    private UserRepository repository;

    public UserViewModelFactory(UserRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(repository);
    }
}
