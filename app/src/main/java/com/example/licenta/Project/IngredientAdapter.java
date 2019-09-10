package com.example.licenta.Project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.licenta.Models.Ingredient;
import com.example.licenta.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> mIngredients;
    private OnItemClickListener listener;

    public IngredientAdapter(List<Ingredient> mIngredients) {
        this.mIngredients = mIngredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient current = mIngredients.get(position);
        holder.textViewName.setText(current.getName());
        String quantity = String.format("%s", current.getQuantity());
        holder.textViewQuantity.setText(quantity);
        holder.textViewUnit.setText(current.getUnit());
    }

    void setIngredients(List<Ingredient> ingredients) {
        mIngredients = ingredients;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder{
        private final TextView textViewName;
        private final TextView textViewQuantity;
        private final TextView textViewUnit;


        private IngredientViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewUnit = itemView.findViewById(R.id.textViewUnit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mIngredients.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Ingredient ingredient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
