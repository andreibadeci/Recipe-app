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

public class EditIngredientFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_edit_ingredient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle bundle = this.getArguments();

        mEditTextName = view.findViewById(R.id.editTextName);
        mEditTextName.setText(bundle.getString("NAME"));
        mEditTextQuantity = view.findViewById(R.id.editTextQuantity);
        mEditTextQuantity.setText(bundle.getString("QUANTITY"));
        mEditTextUnit = view.findViewById(R.id.editTextUnit);
        mEditTextUnit.setText(bundle.getString("UNIT"));

        mIngredientViewModel = ViewModelProviders.of(this).get(IngredientViewModel.class);


        final Button buttonUpdate = view.findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditTextName.getText())) {
                    mEditTextName.setError("Please fill he input");
                } else if (TextUtils.isEmpty(mEditTextQuantity.getText())) {
                    mEditTextQuantity.setError("Please fill he input");
                } else {
                    int id = bundle.getInt("ID");
                    String name = mEditTextName.getText().toString();
                    String quantityString = mEditTextQuantity.getText().toString();
                    double quantity = Double.parseDouble(mEditTextQuantity.getText().toString());
                    String unit = mEditTextUnit.getText().toString();

                    Ingredient ingredient = new Ingredient(id, name, quantity, unit);
                    mIngredientViewModel.update(ingredient);

                    getFragmentManager().popBackStack();
                }
            }
        });

        final Button buttonDelete = view.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = bundle.getInt("ID");
                String name = mEditTextName.getText().toString();
                double quantity = Double.parseDouble(mEditTextQuantity.getText().toString());
                String unit = mEditTextUnit.getText().toString();

                Ingredient ingredient = new Ingredient(id, name, quantity, unit);
                mIngredientViewModel.delete(ingredient);

                getFragmentManager().popBackStack();
            }
        });
    }
}
