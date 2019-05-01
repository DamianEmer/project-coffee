package com.dezc.coffeesaleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dezc.coffeesaleapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView email;
    private TextView password;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();

        mAuth = FirebaseAuth.getInstance();
    }

    public void onRegisterNow(View view) {
        Intent intentRegister = new Intent(this, SignUpActivity.class);
        startActivity(intentRegister);
    }

    public void loginUser(View view) {

        String emailL, passwordL;
        emailL = email.getText().toString();
        passwordL = password.getText().toString();

        if (TextUtils.isEmpty(emailL) && TextUtils.isEmpty(passwordL)) {
            Toast.makeText(getApplicationContext(), "Ingresa tus datos", Toast.LENGTH_LONG).show();
        } else {
/*
        if(){
            Toast.makeText(getApplicationContext(), "Debes ingresar tu contraseña", Toast.LENGTH_LONG);
        }*/

            mAuth.signInWithEmailAndPassword(emailL, passwordL)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Bienvenido!", Toast.LENGTH_LONG).show();

                            Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intentHome);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_LONG).show();
                        }
                    });

        }
    }

    public void initUI() {
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        loginBtn = findViewById(R.id.loginBtn);
    }
}


