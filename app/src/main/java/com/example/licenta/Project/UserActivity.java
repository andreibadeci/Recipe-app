package com.example.licenta.Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.licenta.R;
import com.example.licenta.Utils.Constants;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();

        String fragmentTag = intent.getStringExtra("FRAGMENT");

        if (fragmentTag.equals(Constants.FRAGMENT_REGISTER)){
            Fragment registerFragment = new RegisterFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.userFragment, registerFragment).commit();
        }

        else {
            Fragment loginFragment = new LoginFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.userFragment, loginFragment).commit();
        }
    }
}
