package com.example.licenta.Project;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.licenta.Models.User;


public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    public UserViewModel(Application application) {
        super(application);
        mRepository = new UserRepository(application);
    }

    void insert(User user) {
        mRepository.insert(user);
    }

    void update(User user) { mRepository.update(user); }

    void delete(User user) { mRepository.delete(user); }
}
