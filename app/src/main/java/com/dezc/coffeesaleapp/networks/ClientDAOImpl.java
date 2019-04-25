package com.dezc.coffeesaleapp.networks;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dezc.coffeesaleapp.interfaces.ClientDAO;
import com.dezc.coffeesaleapp.models.Client;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientDAOImpl implements ClientDAO {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    public void onSaveClient(Client client, Context context) {
        auth.createUserWithEmailAndPassword(client.getEmail(), client.getPassword())
                .addOnSuccessListener((AuthResult authResult) -> {
                    myRef.child("clients").child(authResult.getUser().getUid()).setValue(client);
                    Log.i("ClientDAOImpl","SE GUARDO SATISFACTORIAMENTE");
                    Toast.makeText(context, "REGISTRADO EXISTOSAMENTE!!!", Toast.LENGTH_LONG).show();
                }).addOnCanceledListener(() -> {
            Log.i("ClientDAOImpl","Cancelado");
        }).addOnFailureListener((f) -> {
            Toast.makeText(context, "ERROR AL REGISTRARSE", Toast.LENGTH_LONG).show();
            Log.i("ClientDAOImpl","Error" + f.getLocalizedMessage(), f.fillInStackTrace());
        });
    }
}
