package com.example.licenta.Project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.licenta.Models.User;
import com.example.licenta.R;
import com.example.licenta.Utils.AsyncResult;
import com.example.licenta.Utils.Constants;

public class Main2Activity extends AppCompatActivity implements AsyncResult {

    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, UserActivity.class);
                intent.putExtra("FRAGMENT", Constants.FRAGMENT_REGISTER);
                startActivityForResult(intent, Constants.ACTIVITY_REGISTER_REQUEST_CODE);
            }
        });


        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, UserActivity.class);
                intent.putExtra("FRAGMENT", Constants.FRAGMENT_LOGIN);
                startActivityForResult(intent, Constants.ACTIVITY_LOGIN_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.ACTIVITY_REGISTER_REQUEST_CODE && resultCode == RESULT_OK){
            String firstName = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(), "FIRST_NAME");
            String lastName = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(), "LAST_NAME");
            String email = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(), "EMAIL");
            String password = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(), "PASSWORD");
            User user = new User(firstName, lastName, email, password);
            mUserViewModel.insert(user);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("ID", user.getId());
            startActivity(intent);
        } else if (requestCode == Constants.ACTIVITY_LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            String email = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(), "EMAIL");
            String password = ApplicationData.getStringValueFromSharedPreferences(getApplicationContext(), "PASSWORD");
            UserRepository userRepository = new UserRepository(getApplication());
            userRepository.delegate = this;
            userRepository.find(email, password);

        }
        else {
            finish();
        }
    }

    @Override
    public void processFinish(Integer output) {
        Integer id = output;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
