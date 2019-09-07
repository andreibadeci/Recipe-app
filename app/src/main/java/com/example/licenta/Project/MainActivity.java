package com.example.licenta.Project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.licenta.R;
import com.example.licenta.Utils.Constants;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView= findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new RecipeFragment()).commit();
            navigationView.setCheckedItem(R.id.navRecipe);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.navRecipe:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new RecipeFragment(), Constants.FRAGMENT_RECIPE)
                        .addToBackStack("RECIPE_FRAGMENT").commit();
                break;
            case R.id.navIngredient:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new IngredientFragment(), Constants.FRAGMENT_INGREDIENT)
                        .addToBackStack("INGREDIENT_FRAGMENT").commit();
                break;
            case R.id.navProfile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ProfileFragment(), Constants.FRAGMENT_PROFILE)
                        .addToBackStack("PROFILE_FRAGMENT").commit();
                break;
            case R.id.navShare:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navSend:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
