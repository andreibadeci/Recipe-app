package com.example.licenta.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.licenta.R;
import com.example.licenta.Utils.AsyncResult;
import com.example.licenta.Utils.Constants;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //if(ApplicationData.checkIfKeyExists(getActivity().getApplication(), "USER_ID")){

        //}
        //else{
            Button register = view.findViewById(R.id.register);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment registerFragment = new RegisterFragment();

                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, registerFragment).addToBackStack(Constants.FRAGMENT_PROFILE).commit();
                }
            });

            Button login = view.findViewById(R.id.login);
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
