package com.example.licenta.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.licenta.Models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT id FROM user_table WHERE email LIKE :email AND password LIKE :password")
    Integer find(String email, String password);

    @Query("SELECT * FROM user_table WHERE id = :id")
    User getUser(int id);
}
