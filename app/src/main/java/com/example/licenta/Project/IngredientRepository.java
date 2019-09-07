package com.example.licenta.Project;

import android.app.Application;
import android.os.AsyncTask;

import com.example.licenta.Dao.IngredientDao;
import com.example.licenta.Database.IngredientDatabase;
import com.example.licenta.Models.Ingredient;
import com.example.licenta.Utils.AsyncResult;

public class IngredientRepository {

    private IngredientDao mIngredientDao;
    public AsyncResult delegate = null;

    IngredientRepository(Application application) {
        IngredientDatabase db = IngredientDatabase.getDatabase(application);
        mIngredientDao = db.ingredientDao();
    }


    void insert(Ingredient ingredient) {
        new insertAsyncTask(mIngredientDao).execute(ingredient);
    }

    void update(Ingredient ingredient) {
        new updateAsyncTask(mIngredientDao).execute(ingredient);
    }

    void find(String name) { new findAsyncTask(mIngredientDao, delegate).execute(name); }

    void delete(Ingredient ingredient) { new deleteAsyncTask(mIngredientDao).execute(ingredient); }

    private static class insertAsyncTask extends AsyncTask<Ingredient, Void, Void> {

        private IngredientDao mAsyncTaskDao;

        insertAsyncTask(IngredientDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Ingredient... ingredients) {
            mAsyncTaskDao.insert(ingredients[0]);
            return null;
        }
    }

    private static class findAsyncTask extends AsyncTask<String, Void, Integer> {

        private IngredientDao mAsyncTaskDao;
        public AsyncResult delegate = null;

        public findAsyncTask(IngredientDao mAsyncTaskDao, AsyncResult asyncResult) {
            this.mAsyncTaskDao = mAsyncTaskDao;
            this.delegate = asyncResult;
        }

        @Override
        protected Integer doInBackground(String... name) {
            return mAsyncTaskDao.find(name[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            delegate.processFinish(integer);
        }
    }

    private static class updateAsyncTask extends AsyncTask<Ingredient, Void, Void> {

        private IngredientDao mAsyncTaskDao;

        public updateAsyncTask(IngredientDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Ingredient... ingredients) {
            mAsyncTaskDao.update(ingredients[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Ingredient, Void, Void> {

        private IngredientDao mAsyncTaskDao;

        public deleteAsyncTask(IngredientDao mAsyncTaskDao) {
            this.mAsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(Ingredient... ingredients) {
            mAsyncTaskDao.delete(ingredients[0]);
            return null;
        }
    }
}
