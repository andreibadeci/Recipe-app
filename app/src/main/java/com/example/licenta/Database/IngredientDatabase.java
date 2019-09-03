package com.example.licenta.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.licenta.Dao.IngredientDao;
import com.example.licenta.Models.Ingredient;

@Database(entities = {Ingredient.class}, version = 1, exportSchema = false)
public abstract class IngredientDatabase extends RoomDatabase {

    public abstract IngredientDao ingredientDao();

    private static volatile IngredientDatabase INSTANCE;

    public static IngredientDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (IngredientDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IngredientDatabase.class, "ingredient_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final IngredientDao mDao;

        PopulateDbAsync(IngredientDatabase db) {
            mDao = db.ingredientDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Ingredient ingredient = new Ingredient("Rosie", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Mar", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Marar", 0, "fire");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Usturoi", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Zahar", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Vanata", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Mazare", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Pepene galben", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Telina", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Ardei gras", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Dovleac", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Oua", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Capsuni", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Lapte", 0, "l");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Zmeura", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Otet", 0, "ml");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Masline", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Portocala", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Patrunjel", 0, "fire");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Faina", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Malai", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Castraveti", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Varza", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Ulei", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Paste", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Leustean", 0, "fire");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Orez", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Pepene rosu", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Ceapa", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Fasole", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Conopida", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Sare", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Piper", 0, "");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Morcov", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Cartofi", 0, "g");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Piept de pui", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Piept de porc", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Aripioare de pui", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Ceafa de porc", 0, "bucati");
            mDao.insert(ingredient);
            ingredient = new Ingredient("Carne de vita", 0, "bucati");
            mDao.insert(ingredient);
            return null;
        }
    }
}
