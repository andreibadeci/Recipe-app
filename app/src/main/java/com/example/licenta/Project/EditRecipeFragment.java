package com.example.licenta.Project;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.licenta.Models.Recipe;
import com.example.licenta.R;

public class EditRecipeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText mEditTextName;
    private Spinner mSpinnerPrep;
    private Spinner mSpinnerType;
    private EditText mEditTextNote;
    private EditText mEditTextPrepTime;
    private EditText mEditTextIngredients;

    private List<String> mSpinnerPrepData;
    private List<String> mSpinnerTypeData;

    private ArrayAdapter<String> mAdapterSpinnerPrep;
    private ArrayAdapter<String> mAdapterSpinnerType;

    private RecipeViewModel mRecipeViewModel;

    @Override
    public void onAttach(Context context) {
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
        return inflater.inflate(R.layout.fragment_edit_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle bundle = this.getArguments();

        mEditTextName = view.findViewById(R.id.editTextName);
        mEditTextName.setText(bundle.getString("NAME"));
        mSpinnerPrep = view.findViewById(R.id.spinnerPrep);
        mSpinnerPrep.setSelection(getIndex(mSpinnerPrep, bundle.getString("PREPARATION")));
        mSpinnerType = view.findViewById(R.id.spinnerType);
        mSpinnerType.setSelection(getIndex(mSpinnerType, bundle.getString("TYPE")));
        mEditTextNote = view.findViewById(R.id.editTextNotes);
        mEditTextNote.setText(bundle.getString("NOTE"));
        mEditTextPrepTime = view.findViewById(R.id.editTextPrepTime);
        mEditTextPrepTime.setText(bundle.getString("PREPARATION_TIME"));
        mEditTextIngredients = view.findViewById(R.id.editTextIngredients);
        mEditTextIngredients.setText(bundle.getString("INGREDIENTS"));

        mSpinnerPrepData = getSourcePrep();
        mSpinnerTypeData = getSourceType();

        mAdapterSpinnerPrep = getAdapterPrep();
        mAdapterSpinnerType = getAdapterType();

        mSpinnerPrep.setAdapter(mAdapterSpinnerPrep);
        mSpinnerType.setAdapter(mAdapterSpinnerType);

        mSpinnerPrep.setOnItemSelectedListener(this);
        mSpinnerType.setOnItemSelectedListener(this);

        mSpinnerPrep.setPrompt("How easy it is to make it?");
        mSpinnerType.setPrompt("What kind of a dish it is?");

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        final Button editButton = view.findViewById(R.id.buttonUpdate);
        editButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditTextName.getText())) {
                    mEditTextName.setError("Please fill the input");
                } else if (TextUtils.isEmpty(mEditTextNote.getText())) {
                    mEditTextNote.setError("Please fill the input");
                } else if (TextUtils.isEmpty(mEditTextPrepTime.getText())) {
                    mEditTextPrepTime.setError("Please fill the input");
                } else if (TextUtils.isEmpty(mEditTextIngredients.getText())) {
                    mEditTextIngredients.setError("Please fill the input");
                } else {
                    int id = bundle.getInt("ID");
                    String name = mEditTextName.getText().toString();
                    String prep = mSpinnerPrep.getSelectedItem().toString();
                    String type = mSpinnerType.getSelectedItem().toString();
                    String note = mEditTextNote.getText().toString();
                    String prepTime = mEditTextPrepTime.getText().toString();
                    String ingredientString = mEditTextIngredients.getText().toString();

                    Integer userId = ApplicationData.getIntValueFromSharedPreferences(getActivity().getApplication(), "USER_ID");

                    List<String> aux = Arrays.asList(ingredientString.split("\\s*,\\s*"));
                    Iterator<String> iterator = aux.iterator();
                    List<String> aux2 = new ArrayList<>();
                    while (iterator.hasNext()) {
                        aux2.add(iterator.next());
                    }
                    iterator = aux2.iterator();
                    List<String> ingredientList = new ArrayList<>();
                    while (iterator.hasNext()) {
                        aux = Arrays.asList(iterator.next().split("\\s* \\s*"));
                        ingredientList.add(aux.get(0));
                        ingredientList.add(aux.get(1));
                    }
                    ArrayList<String> ingredients = new ArrayList<>(ingredientList);

                    Recipe recipe = new Recipe(id, name, prep, note, type, prepTime, ingredients, userId);
                    mRecipeViewModel.update(recipe);

                    getFragmentManager().popBackStack();
                }
            }
        });

        final Button deleteButton = view.findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int id = bundle.getInt("ID");
                String name = mEditTextName.getText().toString();
                String prep = mSpinnerPrep.getSelectedItem().toString();
                String type = mSpinnerType.getSelectedItem().toString();
                String note = mEditTextNote.getText().toString();
                String prepTime = mEditTextPrepTime.getText().toString();
                String ingredientString = mEditTextIngredients.getText().toString();

                Integer userId = ApplicationData.getIntValueFromSharedPreferences(getActivity().getApplication(), "USER_ID");

                List<String> aux = Arrays.asList(ingredientString.split("\\s*,\\s*"));
                Iterator<String> iterator = aux.iterator();
                List<String> aux2 = new ArrayList<>();
                while (iterator.hasNext()) {
                    aux2.add(iterator.next());
                }
                iterator = aux2.iterator();
                List<String> ingredientList = new ArrayList<>();
                while (iterator.hasNext()) {
                    aux = Arrays.asList(iterator.next().split("\\s* \\s*"));
                    ingredientList.add(aux.get(0));
                    ingredientList.add(aux.get(1));
                }
                ArrayList<String> ingredients = new ArrayList<>(ingredientList);

                Recipe recipe = new Recipe(id, name, prep, note, type, prepTime, ingredients, userId);
                mRecipeViewModel.delete(recipe);

                getFragmentManager().popBackStack();
            }
        });

    }

    private ArrayAdapter<String> getAdapterType() {
        return new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mSpinnerTypeData);
    }

    private ArrayAdapter<String> getAdapterPrep() {
        return new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mSpinnerPrepData);
    }

    private List<String> getSourceType() {
        List<String> type = new ArrayList<>();
        type.add("Breakfast");
        type.add("Main dish");
        type.add("Dessert");
        type.add("Salad");
        type.add("Starters");

        return type;
    }

    private List<String> getSourcePrep() {
        List<String> preparation = new ArrayList<>();
        preparation.add("Very easy");
        preparation.add("Easy");
        preparation.add("Average");
        preparation.add("Hard");
        preparation.add("Very hard");

        return preparation;
    }

    private int getIndex(Spinner spinner, String mString){

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i ++){
            if (spinner.getItemAtPosition(i).equals(mString)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

}
