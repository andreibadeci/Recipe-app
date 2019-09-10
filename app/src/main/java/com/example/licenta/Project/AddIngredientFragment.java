package com.example.licenta.Project;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.licenta.Models.Ingredient;
import com.example.licenta.R;

public class AddIngredientFragment extends Fragment {

    private EditText mEditTextName;
    private EditText mEditTextQuantity;
    private EditText mEditTextUnit;

    private IngredientViewModel mIngredientViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_ingredient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditTextName = view.findViewById(R.id.editTextName);
        mEditTextQuantity = view.findViewById(R.id.editTextQuantity);
        mEditTextUnit = view.findViewById(R.id.editTextUnit);

        mIngredientViewModel = ViewModelProviders.of(this).get(IngredientViewModel.class);


        final Button button = view.findViewById(R.id.buttonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditTextName.getText())) {
                    mEditTextName.setError("Please fill he input");
                } else if (TextUtils.isEmpty(mEditTextQuantity.getText())) {
                    mEditTextQuantity.setError("Please fill he input");
                } else if (TextUtils.isEmpty(mEditTextUnit.getText())) {
                    mEditTextUnit.setError("Please fill he input");
                } else {
                    String name = mEditTextName.getText().toString();
                    String quantityString = mEditTextQuantity.getText().toString();
                    double quantity = Double.parseDouble(mEditTextQuantity.getText().toString());
                    String unit = mEditTextUnit.getText().toString();

                    Ingredient ingredient = new Ingredient(name, quantity, unit);
                    mIngredientViewModel.insert(ingredient);

                    getFragmentManager().popBackStack();
                }
            }
        });
    }
}
