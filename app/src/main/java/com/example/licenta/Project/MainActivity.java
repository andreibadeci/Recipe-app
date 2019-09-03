package com.example.licenta.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.licenta.Models.Recipe;
import com.example.licenta.R;
import com.example.licenta.Utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RecipeViewModel mRecipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> mRecipes = Collections.emptyList();

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);
        //parseJSON();

        mRecyclerView = findViewById(R.id.recyclerViewRecipes);
        mRecipeAdapter = new RecipeAdapter(mRecipes);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mRecipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable final List<Recipe> recipes) {
                mRecipeAdapter.setRecipes(recipes);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.putExtra("FRAGMENT", Constants.FRAGMENT_ADD_RECIPE);
                startActivityForResult(intent, Constants.ACTIVITY_ADD_RECIPE_REQUEST_CODE);
            }
        });

        mRecipeAdapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe recipe) {
                Intent previous = getIntent();
                Integer userId = previous.getIntExtra("ID", -1);
                if (userId.equals(recipe.getUserId())) {
                    Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                    intent.putExtra("ID", recipe.getId());
                    intent.putExtra("NAME", recipe.getName());
                    intent.putExtra("PREPARATION", recipe.getPreparation());
                    intent.putExtra("TYPE", recipe.getType());
                    intent.putExtra("NOTE", recipe.getNote());
                    intent.putExtra("PREPARATION_TIME", recipe.getPrepTime());
                    ArrayList<String> ingredients = recipe.getIngredients();
                    intent.putStringArrayListExtra("INGREDIENTS", (ingredients));
                    intent.putExtra("FRAGMENT", Constants.FRAGMENT_EDIT_RECIPE);
                    startActivityForResult(intent, Constants.ACTIVITY_EDIT_RECIPE_REQUEST_CODE);
                }
                else {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.recipe_not_owned,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /*private void parseJSON() {
        String url = "https://api.myjson.com/bins/125zhc";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("recipe");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);


                                String name = hit.getString("name");
                                String preparation = hit.getString("preparation");
                                String note = hit.getString("note");
                                String type = hit.getString("type");
                                String prepTime = hit.getString("prepTime");
                                String ingredients = hit.getString("ingredients");

                                Recipe recipe = new Recipe(name, preparation, note, type, prepTime, ingredients);
                                mRecipeViewModel.insert(recipe);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }*/

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = getIntent();

        if (requestCode == Constants.ACTIVITY_ADD_RECIPE_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"NAME");
            String prep = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"PREPARATION");
            String type = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"TYPE");
            String note =  ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"NOTE");
            String prepTime =  ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"PREPARATION_TIME");
            String ingredientString =  ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"INGREDIENTS");
            Integer userId = intent.getIntExtra("ID", -1);

            List<String> aux = Arrays.asList(ingredientString.split("\\s*,\\s*"));
            Iterator<String> iterator = aux.iterator();
            List<String> aux2 = new ArrayList<>();
            while (iterator.hasNext()) {
                aux2.add(iterator.next());
            }
            iterator = aux2.iterator();
            List<String> ingredientList = new ArrayList<>();
            while (iterator.hasNext()){
                aux = Arrays.asList(iterator.next().split("\\s* \\s*"));
                ingredientList.add(aux.get(0));
                ingredientList.add(aux.get(1));
            }
            ArrayList<String> ingredients = new ArrayList<>(ingredientList);

            Recipe recipe = new Recipe(name, prep, note, type, prepTime, ingredients, userId);
            mRecipeViewModel.insert(recipe);
        } else if (requestCode == Constants.ACTIVITY_EDIT_RECIPE_REQUEST_CODE && resultCode == RESULT_OK) {
            String idString = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"ID");
            int id = Integer.parseInt(idString);
            String name = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"NAME");
            String prep = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"PREPARATION");
            String type = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"TYPE");
            String note =  ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"NOTE");
            String prepTime =  ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"PREPARATION_TIME");
            String ingredientString =  ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(),"INGREDIENTS");
            String buttonPressed = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(), "BUTTON");
            Integer userId = intent.getIntExtra("ID", -1);

            List<String> aux = Arrays.asList(ingredientString.split("\\s*,\\s*"));
            Iterator<String> iterator = aux.iterator();
            List<String> aux2 = new ArrayList<>();
            while (iterator.hasNext()) {
                aux2.add(iterator.next());
            }
            iterator = aux2.iterator();
            List<String> ingredientList = new ArrayList<>();
            while (iterator.hasNext()){
                aux = Arrays.asList(iterator.next().split("\\s* \\s*"));
                ingredientList.add(aux.get(0));
                ingredientList.add(aux.get(1));
            }
            ArrayList<String> ingredients = new ArrayList<>(ingredientList);

            Recipe recipe = new Recipe(id, name, prep, note, type, prepTime, ingredients, userId);
            if (buttonPressed.equals("Edit")){
                mRecipeViewModel.update(recipe);
            }
            else {
                mRecipeViewModel.delete(recipe);
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}

