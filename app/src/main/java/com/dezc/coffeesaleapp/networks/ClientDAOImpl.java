package com.dezc.coffeesaleapp.networks;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dezc.coffeesaleapp.interfaces.ClientDAO;
import com.dezc.coffeesaleapp.models.Client;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ClientDAOImpl implements ClientDAO {

    StorageReference storageReference = FirebaseStorage.getInstance().getReference("clients");
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    public void onSaveClient(Client client, Context context, Uri imageUri) {
        if (imageUri != null) {
            auth.createUserWithEmailAndPassword(client.getEmail(), client.getPassword())
                    .addOnSuccessListener((AuthResult authResult) -> {

                        StorageReference fileReference = storageReference.child(authResult.getUser().getUid() + ".jpg");

                        fileReference.putFile(imageUri)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        myRef.child("clients").child(authResult.getUser().getUid()).setValue(client);
                                        Toast.makeText(context, "REGISTRADO EXISTOSAMENTE!!!", Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                    }
                                });
                    }).addOnCanceledListener(() -> {
                Log.i("ClientDAOImpl", "Cancelado");
            }).addOnFailureListener((f) -> {
                Toast.makeText(context, "ERROR AL REGISTRARSE", Toast.LENGTH_LONG).show();
                Log.i("ClientDAOImpl", "Error" + f.getLocalizedMessage(), f.fillInStackTrace());
            });
        } else {

            Toast.makeText(context, "Datos vacios", Toast.LENGTH_SHORT).show();
        }
    }
}
