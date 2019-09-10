package com.example.licenta.Project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.licenta.Models.Ingredient;
import com.example.licenta.R;
import com.example.licenta.Utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

public class IngredientFragment extends Fragment {

    private IngredientViewModel mIngredientViewModel;
    private RecyclerView mRecyclerView;
    private IngredientAdapter mIngredientAdapter;
    private List<Ingredient> mIngredients = Collections.emptyList();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recyclerViewIngredients);
        mIngredientAdapter = new IngredientAdapter(mIngredients);
        mRecyclerView.setAdapter(mIngredientAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mIngredientViewModel = ViewModelProviders.of(this).get(IngredientViewModel.class);

        mIngredientViewModel.getAllIngredients().observe(this, new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(@Nullable final List<Ingredient> ingredients) {
                mIngredientAdapter.setIngredients(ingredients);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addFragment = new AddIngredientFragment();

                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, addFragment).addToBackStack(Constants.FRAGMENT_INGREDIENT).commit();
            }
        });

        mIngredientAdapter.setOnItemClickListener(new IngredientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ingredient ingredient) {
                int id = ingredient.getId();
                String name = ingredient.getName();
                String quantity = String.format("%s", ingredient.getQuantity());
                String unit = ingredient.getUnit();

                Bundle bundle = new Bundle();
                bundle.putInt("ID", id);
                bundle.putString("NAME", name);
                bundle.putString("QUANTITY", quantity);
                bundle.putString("UNIT", unit);

                Fragment editFragment = new EditIngredientFragment();
                editFragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, editFragment).addToBackStack(Constants.FRAGMENT_INGREDIENT).commit();

            }
        });
    }
}
