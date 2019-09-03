package com.example.licenta.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.licenta.Models.User;

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
}
