package id.tiketbus.tiketbus.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.tiketbus.tiketbus.MainActivity;
import id.tiketbus.tiketbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class signin extends Fragment {
    private Button btnSignIn;

    public signin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        btnSignIn = view.findViewById(R.id.btn_sign_in);
        login();
        return view;
    }

    private void login() {

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }

}
