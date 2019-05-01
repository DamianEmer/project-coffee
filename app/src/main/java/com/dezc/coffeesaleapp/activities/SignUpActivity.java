package com.dezc.coffeesaleapp.activities;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.fragments.LoginFragment;
import com.dezc.coffeesaleapp.models.Client;
import com.dezc.coffeesaleapp.networks.ClientDAOImpl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements LoginFragment.EmailChanged, LoginFragment.PasswordChanged {

    private static final int PICK_IMAGE_REQUEST = 1;

    private long clientId;
    private TextView name;
    private TextView lastname;
    private TextView secondaLastname;
    private TextView numberWhatsapp;
    private String email;
    private String password;

    private Button chooseImageButton;
    private ImageView profilePhoto;
    private ProgressBar progressBar;

    private Uri imageUri;

    private ClientDAOImpl clientDaoImpl = new ClientDAOImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        chooseImageButton = findViewById(R.id.chooseButton);
        profilePhoto = findViewById(R.id.profilePhotoimageView);
        progressBar = findViewById(R.id.signUpProgressBar);

        chooseImageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

    }

    public void onSaveClient(View view) {
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
                password,
                (imageUri != null) ? imageUri.toString() : ""
        );

        if (validateInputEmail(email) && validateInputPassword(password)
                && validateInputNumberWhatsapp(numberWhatsapp.getText().toString())) {
            clientDaoImpl.onSaveClient(client, getApplicationContext(), imageUri);
        }

    }

    public void emailAndPassword(String email, String password) {

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

    /*public void onChooseImage(View view){
        openFileChooser();
    }*/

    private boolean validateInputEmail(String email) {
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailPattern);
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            } else {
                Toast.makeText(this, "Email no válido", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Inserte un correo eléctronico", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean validateInputPassword(String password) {
        if (password != null) {
            if (password.length() >= 8) {
                return true;
            } else {
                Toast.makeText(this, "La contraseña debe ser mayor a 8 caracteres", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Ingrese una password", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validateInputNumberWhatsapp(String numberWhatsapp) {
        if (numberWhatsapp != null) {
            if (numberWhatsapp.length() == 10) {
                return true;
            } else {
                Toast.makeText(this, "Número invalido", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Ingrese un número de Whatsapp", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();

            Glide.with(this).load(imageUri).into(profilePhoto);
        }
    }
}
