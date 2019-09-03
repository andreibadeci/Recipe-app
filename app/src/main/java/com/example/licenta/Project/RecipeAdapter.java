package com.example.licenta.Project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.example.licenta.Models.Recipe;
import com.example.licenta.R;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> mRecipes;
    private OnItemClickListener listener;

    public RecipeAdapter(List<Recipe> mRecipes) {
        this.mRecipes = mRecipes;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe current = mRecipes.get(position);
        holder.textViewName.setText(current.getName());
        holder.textViewPrep.setText(current.getPreparation());
        holder.textViewType.setText(current.getType());
        holder.textViewNotes.setText(current.getNote());
        holder.textViewPrepTime.setText(current.getPrepTime());
        ArrayList<String> aux = current.getIngredients();
        String ingredientsAux = "";
        Iterator<String> iterator = aux.iterator();
        String ingredients = "";
        while (iterator.hasNext()) {
            ingredients = ingredientsAux.concat(iterator.next());
        }
        holder.textViewIngredients.setText(ingredients);
    }

    void setRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder{
        private final TextView textViewName;
        private final TextView textViewPrep;
        private final TextView textViewType;
        private final TextView textViewNotes;
        private final TextView textViewPrepTime;
        private final TextView textViewIngredients;


        private RecipeViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrep = itemView.findViewById(R.id.textViewPrep);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewNotes = itemView.findViewById(R.id.textViewNotes);
            textViewPrepTime = itemView.findViewById(R.id.textViewPrepTime);
            textViewIngredients = itemView.findViewById(R.id.textViewIngredients);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mRecipes.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Recipe recipe);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
