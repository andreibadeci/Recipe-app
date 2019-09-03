package com.example.licenta.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "recipe_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "prep")
    private String mPreparation;

    @NonNull
    @ColumnInfo(name = "mNote")
    private String mNote;

    @NonNull
    @ColumnInfo(name = "mType")
    private String mType;

    @NonNull
    @ColumnInfo(name = "time")
    private String mPrepTime;

    @NonNull
    @ColumnInfo(name = "ingredients")
    private ArrayList<String> mIngredients;

    @NonNull
    @ColumnInfo(name = "userID")
    private Integer mUserId;

    public Recipe(int id, @NonNull String name, @NonNull String preparation, @NonNull String note, @NonNull String type,
                  @NonNull String prepTime, @NonNull ArrayList<String> ingredients, @NonNull Integer userId) {
        this.mId = id;
        this.mName = name;
        this.mPreparation = preparation;
        this.mNote = note;
        this.mType = type;
        this.mPrepTime = prepTime;
        this.mIngredients = ingredients;
        this.mUserId = userId;
    }

    @Ignore public Recipe(@NonNull String name, @NonNull String preparation, @NonNull String note, @NonNull String type,
                          @NonNull String prepTime, @NonNull ArrayList<String> ingredients, @NonNull Integer userId) {
        this.mName = name;
        this.mPreparation = preparation;
        this.mNote = note;
        this.mType = type;
        this.mPrepTime = prepTime;
        this.mIngredients = ingredients;
        this.mUserId = userId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        this.mName = name;
    }

    @NonNull
    public String getPreparation() {
        return mPreparation;
    }

    public void setPreparation(@NonNull String preparation) {
        this.mPreparation = preparation;
    }

    @NonNull
    public String getNote() {
        return mNote;
    }

    public void setNote(@NonNull String note) {
        this.mNote = note;
    }

    @NonNull
    public String getType() {
        return mType;
    }

    public void setType(@NonNull String type) {
        this.mType = type;
    }

    public String getPrepTime() {
        return mPrepTime;
    }

    public void setPrepTime(String prepTime) {
        this.mPrepTime = prepTime;
    }

    @NonNull
    public ArrayList<String> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(@NonNull ArrayList<String> ingredients) { this.mIngredients = ingredients; }

    @NonNull
    public Integer getUserId() { return mUserId; }

    public void setUserId(@NonNull Integer mUserId) {
        this.mUserId = mUserId;
    }
}
