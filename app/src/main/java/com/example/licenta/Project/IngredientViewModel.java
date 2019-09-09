package com.example.licenta.Project;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.licenta.Models.Ingredient;

public class IngredientViewModel extends AndroidViewModel {

    private IngredientRepository mRepository;

    public IngredientViewModel(Application application){
        super(application);
        mRepository = new IngredientRepository(application);
    }

    void insert(Ingredient ingredient) {
        mRepository.insert(ingredient);
    }

    void update(Ingredient ingredient) { mRepository.update(ingredient); }

    void delete(Ingredient ingredient) { mRepository.delete(ingredient); }

}
