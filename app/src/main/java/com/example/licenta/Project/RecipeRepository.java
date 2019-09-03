package com.example.licenta.Project;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

import com.example.licenta.Dao.RecipeDao;
import com.example.licenta.Database.RecipeDatabase;
import com.example.licenta.Models.Recipe;

class RecipeRepository {

    private RecipeDao mRecipeDao;
    private LiveData<List<Recipe>> mAllRecipes;

    RecipeRepository(Application application) {
        RecipeDatabase db = RecipeDatabase.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mAllRecipes = mRecipeDao.getAlphabetizedRecipes();
    }

    LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }

    void insert(Recipe recipe) {
        new insertAsyncTask(mRecipeDao).execute(recipe);
    }

    void update(Recipe recipe) {
        new updateAsyncTask(mRecipeDao).execute(recipe);
    }

    void delete(Recipe recipe) { new deleteAsyncTask(mRecipeDao).execute(recipe); }

    private static class insertAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao mAsyncTaskDao;

        insertAsyncTask(RecipeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Recipe... recipes) {
            mAsyncTaskDao.insert(recipes[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao mAsyncTaskDao;

        public updateAsyncTask(RecipeDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            mAsyncTaskDao.update(recipes[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao mAsyncTaskDao;

        public deleteAsyncTask(RecipeDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            mAsyncTaskDao.delete(recipes[0]);
            return null;
        }
    }
}
