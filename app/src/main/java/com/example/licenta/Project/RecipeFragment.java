package com.example.licenta.Project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.licenta.Models.Recipe;
import com.example.licenta.R;
import com.example.licenta.Utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeFragment extends Fragment {

    private RecipeViewModel mRecipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> mRecipes = Collections.emptyList();

    private RequestQueue mRequestQueue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRequestQueue = Volley.newRequestQueue(getActivity());
        //parseJSON();

        mRecyclerView = view.findViewById(R.id.recyclerViewRecipes);
        mRecipeAdapter = new RecipeAdapter(mRecipes);
        mRecyclerView.setAdapter(mRecipeAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        mRecipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable final List<Recipe> recipes) {
                mRecipeAdapter.setRecipes(recipes);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addFragment = new AddRecipeFragment();

                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, addFragment).addToBackStack(Constants.FRAGMENT_RECIPE).commit();
            }
        });

        mRecipeAdapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe recipe) {
                Integer userId = ApplicationData.getIntValueFromSharedPreferences(getActivity().getApplication(), "USER_ID");
                if (userId.equals(recipe.getUserId())) {
                    int id = recipe.getId();
                    String name = recipe.getName();
                    String preparation = recipe.getPreparation();
                    String type = recipe.getType();
                    String note = recipe.getNote();
                    String prepTime = recipe.getPrepTime();
                    ArrayList<String> ingredients = recipe.getIngredients();

                    Bundle bundle = new Bundle();
                    bundle.putInt("ID", id);
                    bundle.putString("NAME", name);
                    bundle.putString("PREPARATION", preparation);
                    bundle.putString("TYPE", type);
                    bundle.putString("NOTE", note);
                    bundle.putString("PREPARATION_TIME", prepTime);
                    bundle.putStringArrayList("INGREDIENTS", ingredients);

                    Fragment editFragment = new EditRecipeFragment();
                    editFragment.setArguments(bundle);

                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, editFragment).addToBackStack(Constants.FRAGMENT_RECIPE).commit();
                }
                else {
                    Toast.makeText(
                            getActivity().getApplicationContext(),
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

}
