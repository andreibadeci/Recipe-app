package com.example.licenta.Project;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.licenta.Models.Ingredient;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {

    private IngredientRepository mRepository;
    private LiveData<List<Ingredient>> mAllIngredients;

    public IngredientViewModel(Application application){
        super(application);
        mRepository = new IngredientRepository(application);
        mAllIngredients = mRepository.getAllIngredients();
    }

    LiveData<List<Ingredient>> getAllIngredients() { return mAllIngredients; }

    void insert(Ingredient ingredient) {
        mRepository.insert(ingredient);
    }

    void update(Ingredient ingredient) { mRepository.update(ingredient); }

    void delete(Ingredient ingredient) { mRepository.delete(ingredient); }

}
