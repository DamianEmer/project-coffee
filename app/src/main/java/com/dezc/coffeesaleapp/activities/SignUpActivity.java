package com.dezc.coffeesaleapp.activities;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.fragments.LoginFragment;
import com.dezc.coffeesaleapp.models.Client;
import com.dezc.coffeesaleapp.networks.ClientDAOImpl;

import java.util.Date;

public class SignUpActivity extends AppCompatActivity implements LoginFragment.EmailChanged, LoginFragment.PasswordChanged {

    private long clientId;
    private TextView name;
    private TextView lastname;
    private TextView secondaLastname;
    private TextView numberWhatsapp;
    private String email;
    private String password;

    private ClientDAOImpl clientDaoImpl = new ClientDAOImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
/*
        FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction t = manager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        t.add(R.id.signUpContainer, loginFragment);
        t.commit();*/
    }

    public void onSaveClient(View view){
        Date date = new Date();
        name = findViewById(R.id.nameInputEditText);
        lastname = findViewById(R.id.lastnameInputEditText);
        secondaLastname = findViewById(R.id.secondLastnameInputEditText);
        numberWhatsapp = findViewById(R.id.whatsappNumberInputEditText);
        Client client = new Client(
                name.getText().toString(),
                lastname.getText().toString(),
                secondaLastname.getText().toString(),
                numberWhatsapp.getText().toString(),
                email,
                password
        );

        clientDaoImpl.onSaveClient(client, getApplicationContext());
    }

    public void emailAndPassword (String email, String password) {

    }

    @Override
    public void onEmailChanged(String newText) {
        email = newText;
        Log.i("SignUpActivity", newText);
    }


    @Override
    public void onPasswordChanged(String passwordTxt) {
        password = passwordTxt;
        Log.i("SignUpActivity", passwordTxt);
    }
}
