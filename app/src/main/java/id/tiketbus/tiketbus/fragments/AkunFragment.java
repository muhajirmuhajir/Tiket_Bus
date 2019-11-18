package id.tiketbus.tiketbus.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView name = view.findViewById(R.id.tv_name);
        final EditText email = view.findViewById(R.id.et_email);
        final EditText alamat = view.findViewById(R.id.et_alamat);

        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Penumpang").child(uid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    name.setText(dataSnapshot.child("nama").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    alamat.setText(dataSnapshot.child("alamat").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
