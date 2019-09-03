package com.example.licenta.Project;

import android.content.Intent;
import android.os.Bundle;

import com.example.licenta.R;
import com.example.licenta.Utils.Constants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();

        final String fragmentTag = intent.getStringExtra("FRAGMENT");

        if(fragmentTag.equals(Constants.FRAGMENT_ADD_RECIPE)){

            Fragment addFragment = new AddRecipeFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.recipeFragment, addFragment).commit();
        } else if (fragmentTag.equals(Constants.FRAGMENT_EDIT_RECIPE)){

            int id = intent.getIntExtra("ID", -1);

            if(id == -1){
                setResult(RESULT_CANCELED);
            } else {

                ArrayList<String> ingredients = new ArrayList<>();

                String name = intent.getStringExtra("NAME");
                String preparation = intent.getStringExtra("PREPARATION");
                String type = intent.getStringExtra("TYPE");
                String note = intent.getStringExtra("NOTE");
                String prepTime = intent.getStringExtra("PREPARATION_TIME");
                ingredients = intent.getStringArrayListExtra("INGREDIENTS");

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

                getSupportFragmentManager().beginTransaction().replace(R.id.recipeFragment, editFragment).commit();
            }
        }
    }
}
