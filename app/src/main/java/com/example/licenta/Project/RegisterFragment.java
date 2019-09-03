package com.example.licenta.Project;

import android.app.Activity;
import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.licenta.R;

public class RegisterFragment extends Fragment {

    private EditText mEditTextFirstName;
    private EditText mEditTextLastName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private CheckBox mCheckBoxAccept;
    private Button mButton;

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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditTextFirstName = view.findViewById(R.id.editTextFirstName);
        mEditTextLastName = view.findViewById(R.id.editTextLastName);
        mEditTextEmail = view.findViewById(R.id.editTextEmail);
        mEditTextPassword = view.findViewById(R.id.editTextPassword);
        mCheckBoxAccept = view.findViewById(R.id.checkbox_accept);
        mButton = view.findViewById(R.id.buttonAccept);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditTextFirstName.getText())) {
                    mEditTextFirstName.setError("Please fill the input");
                } else if (TextUtils.isEmpty(mEditTextLastName.getText())){
                    mEditTextLastName.setError("Please fill the input");
                } else if (TextUtils.isEmpty(mEditTextEmail.getText()) && !Patterns.EMAIL_ADDRESS.matcher(mEditTextEmail.getText()).matches()){
                    mEditTextEmail.setError("Please fill the input with a correct email address");
                } else if (TextUtils.isEmpty(mEditTextPassword.getText())) {
                    mEditTextPassword.setError("Please provide a password");
                } else if(!mCheckBoxAccept.isChecked()){
                    mCheckBoxAccept.setError("Please accept Terms & Conditions");
                } else {
                    String firstName = mEditTextFirstName.getText().toString();
                    String lastName = mEditTextLastName.getText().toString();
                    String email = mEditTextEmail.getText().toString();
                    String password = mEditTextPassword.getText().toString();
                    ApplicationData.setStringValueInSharedPreferences(getActivity(), "FIRST_NAME", firstName);
                    ApplicationData.setStringValueInSharedPreferences(getActivity(), "LAST_NAME", lastName);
                    ApplicationData.setStringValueInSharedPreferences(getActivity(), "EMAIL", email);
                    ApplicationData.setStringValueInSharedPreferences(getActivity(), "PASSWORD", password);
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                }
            }
        });

    }
}