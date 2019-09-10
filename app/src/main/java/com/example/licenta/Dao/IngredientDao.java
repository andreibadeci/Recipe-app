package com.example.licenta.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.licenta.Models.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {

    @Query("SELECT * from ingredient_table ORDER BY name ASC")
    LiveData<List<Ingredient>> getAlphabetizedIngredients();

    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("SELECT id FROM ingredient_table WHERE name LIKE :name")
    Integer find(String name);
}
