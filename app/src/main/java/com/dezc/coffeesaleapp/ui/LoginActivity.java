package com.dezc.coffeesaleapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.ui.utils.callbacks.EmailChanged;
import com.dezc.coffeesaleapp.ui.utils.callbacks.PasswordChanged;
import com.dezc.coffeesaleapp.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements EmailChanged, PasswordChanged {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public void onEmailChanged(String newText) {
    }

    @Override
    public void onPasswordChanged(String passwordTxt) {
    }
}
