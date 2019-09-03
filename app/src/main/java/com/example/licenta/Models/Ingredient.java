package com.example.licenta.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_table")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "quantity")
    private double mQuantity;

    @ColumnInfo(name = "unit")
    private String mUnit;

    public Ingredient(int mId, @NonNull String mName, @NonNull double mQuantity, String mUnit) {
        this.mId = mId;
        this.mName = mName;
        this.mQuantity = mQuantity;
        this.mUnit = mUnit;
    }

    @Ignore public Ingredient(@NonNull String mName, @NonNull double mQuantity, String mUnit) {
        this.mName = mName;
        this.mQuantity = mQuantity;
        this.mUnit = mUnit;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    @NonNull
    public String getUnit() {
        return mUnit;
    }

    public void setUnit(@NonNull String mUnit) {
        this.mUnit = mUnit;
    }
}
