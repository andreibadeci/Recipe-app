package com.example.licenta.Project;

import android.app.Activity;
import android.content.Context;
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

import com.example.licenta.R;


public class LoginFragment extends Fragment {

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditTextEmail = view.findViewById(R.id.editTextEmail);
        mEditTextPassword = view.findViewById(R.id.editTextPassword);
        mButton = view.findViewById(R.id.buttonAccept);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditTextPassword.getText())) {
                    mEditTextPassword.setError("Please fill the input");
                } else if (TextUtils.isEmpty(mEditTextEmail.getText()) && !Patterns.EMAIL_ADDRESS.matcher(mEditTextEmail.getText()).matches()){
                    mEditTextEmail.setError("Please fill the input with a correct email address");
                } else {
                    String email = mEditTextEmail.getText().toString();
                    String password = mEditTextPassword.getText().toString();
                    ApplicationData.setStringValueInSharedPreferences(getActivity(), "EMAIL", email);
                    ApplicationData.setStringValueInSharedPreferences(getActivity(), "PASSWORD", password);
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();
                }
            }
        });

    }
}
