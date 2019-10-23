package id.tiketbus.tiketbus.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.tiketbus.tiketbus.MainActivity;
import id.tiketbus.tiketbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class signin extends Fragment {
    private Button btnSignIn;
    private EditText et_email, et_password;
    private FirebaseAuth mAuth;

    public signin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        mAuth = FirebaseAuth.getInstance();

        btnSignIn = view.findViewById(R.id.btn_sign_in);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        return view;
    }
    private Boolean isInputEmpty(String input) {
        if (TextUtils.isEmpty(input)) {
            return true;
        }
        return false;
    }
    private static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isPasswordValid(String password) {
        if (password.toCharArray().length < 6) {
            return false;
        }
        return true;
    }
    private boolean isPasswordeasy(String password){
        int counter = 0;
        for (int i = 0; i < password.length(); i++) {
            counter += isOperand(password.charAt(i));
        }
        if (counter >=10){
            return false;
        }
        return true;
    }
    private int isOperand(char x) {
        if (x >= 'a' && x <= 'z'){
            return 1;
        }if (x >= 'A' && x <= 'Z'){
            return 2;
        }if (x >= '0' && x <= '9'){
            return 3;
        }
        return 0;}

    private void login() {
        final String email = et_email.getText().toString();
        final String password = et_password.getText().toString();

        if(!isEmailValid(email)){
            et_email.setError("Email tidak valid");
        }else if (isPasswordeasy(password)&& !isPasswordValid(password)){
            et_password.setError("Password tidak valid");
        }else {


            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }else {
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Login Gagal")
                                        .setMessage("Email atau Password yang anda masukkan salah")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Continue with delete operation
                                                et_password.setText("");
                                            }
                                        }).show();
                            }
                        }
                    });
        }


    }

}
