package id.tiketbus.tiketbus.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import id.tiketbus.tiketbus.LoginActivity;
import id.tiketbus.tiketbus.R;
import id.tiketbus.tiketbus.SplashActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment implements View.OnClickListener{
    private FirebaseAuth _mAuth;

    private Button _btnlogout;


    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_akun, container, false);
        _mAuth = FirebaseAuth.getInstance();

        _btnlogout = v.findViewById(R.id.btn_logout);
        _btnlogout.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_logout :
                new AlertDialog.Builder(getContext())
                        .setTitle("Log Out")
                        .setMessage("Apakah anda ingin keluar?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                _mAuth.signOut();
                                startActivity(new Intent(getContext(), SplashActivity.class));
                                getActivity().finish();
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
                break;
        }
    }
}
