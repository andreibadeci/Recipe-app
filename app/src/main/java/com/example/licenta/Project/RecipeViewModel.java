package com.example.licenta.Project;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.licenta.Models.Recipe;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository mRepository;
    private LiveData<List<Recipe>> mAllRecipes;

    public RecipeViewModel(Application application) {
        super(application);
        mRepository = new RecipeRepository(application);
        mAllRecipes = mRepository.getAllRecipes();
    }

    LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }

    void insert(Recipe recipe) {
        mRepository.insert(recipe);
    }

    void update(Recipe recipe) { mRepository.update(recipe); }

    void delete(Recipe recipe) { mRepository.delete(recipe); }
}
