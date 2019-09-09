package com.example.licenta.Project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.licenta.Models.User;
import com.example.licenta.R;
import com.example.licenta.Utils.AsyncResult;
import com.example.licenta.Utils.Constants;

public class ProfileFragment extends Fragment implements AsyncResult {

    private EditText mEditTextFirstName;
    private EditText mEditTextLastName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButton;

    private UserViewModel mUserViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (ApplicationData.checkIfKeyExists(getActivity().getApplication(), "USER_ID")) {
            LinearLayout linearLayout = view.findViewById(R.id.linearLayoutProfile);
            linearLayout.setVisibility(View.VISIBLE);

            mEditTextFirstName = view.findViewById(R.id.editTextFirstName);
            mEditTextLastName = view.findViewById(R.id.editTextLastName);
            mEditTextEmail = view.findViewById(R.id.editTextEmail);
            mEditTextPassword = view.findViewById(R.id.editTextPassword);
            mButton = view.findViewById(R.id.buttonAccept);

            mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

            UserRepository userRepository = new UserRepository(getActivity().getApplication());
            userRepository.delegate = this;

            userRepository.getUser(ApplicationData.getIntValueFromSharedPreferences(getActivity().getApplication(), "USER_ID"));

            mEditTextFirstName.setText(ApplicationData.getStringValueFromSharedPreferences(getActivity().getApplication(), "FIRST_NAME"));
            mEditTextLastName.setText(ApplicationData.getStringValueFromSharedPreferences(getActivity().getApplication(), "LAST_NAME"));
            mEditTextEmail.setText(ApplicationData.getStringValueFromSharedPreferences(getActivity().getApplication(), "EMAIL"));
            mEditTextPassword.setText(ApplicationData.getStringValueFromSharedPreferences(getActivity().getApplication(), "PASSWORD"));

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(mEditTextFirstName.getText())) {
                        mEditTextFirstName.setError("Please fill the input");
                    } else if (TextUtils.isEmpty(mEditTextLastName.getText())) {
                        mEditTextLastName.setError("Please fill the input");
                    } else if (TextUtils.isEmpty(mEditTextEmail.getText()) && !Patterns.EMAIL_ADDRESS.matcher(mEditTextEmail.getText()).matches()) {
                        mEditTextEmail.setError("Please fill the input with a correct email address");
                    } else if (TextUtils.isEmpty(mEditTextPassword.getText())) {
                        mEditTextPassword.setError("Please provide a password");
                    } else {
                        int id = ApplicationData.getIntValueFromSharedPreferences(getActivity().getApplication(), "USER_ID");

                        String firstName = mEditTextFirstName.getText().toString();
                        String lastName = mEditTextLastName.getText().toString();
                        String email = mEditTextEmail.getText().toString();
                        String password = mEditTextPassword.getText().toString();

                        User user = new User(id, firstName, lastName, email, password);
                        mUserViewModel.update(user);

                        ApplicationData.setIntValueInSharedPreferences(getActivity().getApplication(), "USER_ID", user.getId());

                        getFragmentManager().popBackStack();
                    }
                }
            });

        } else {
            Button register = view.findViewById(R.id.register);
            register.setVisibility(View.VISIBLE);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment registerFragment = new RegisterFragment();

                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, registerFragment).addToBackStack(Constants.FRAGMENT_PROFILE).commit();
                }
            });

            Button login = view.findViewById(R.id.login);
            login.setVisibility(View.VISIBLE);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment loginFragment = new LoginFragment();

                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, loginFragment).addToBackStack(Constants.FRAGMENT_PROFILE).commit();
                }
            });
            //}
        }
    }

    @Override
    public void processFinish(Integer output) {
        ;
    }

    @Override
    public void processFinish(User user) {
        ApplicationData.setStringValueInSharedPreferences(getActivity().getApplication(), "FIRST_NAME", user.getFirstName());
        ApplicationData.setStringValueInSharedPreferences(getActivity().getApplication(), "LAST_NAME", user.getLastName());
        ApplicationData.setStringValueInSharedPreferences(getActivity().getApplication(), "EMAIL", user.getEmail());
        ApplicationData.setStringValueInSharedPreferences(getActivity().getApplication(), "PASSWORD", user.getPassword());
    }
}
