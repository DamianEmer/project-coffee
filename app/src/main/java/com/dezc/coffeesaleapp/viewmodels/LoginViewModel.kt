package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dezc.coffeesaleapp.models.Client
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class LoginViewModel(application: Application) : AndroidViewModel(application), OnCanceledListener, OnFailureListener {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val mClientsDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("clients")

    private val mClientsStorageReference: StorageReference = FirebaseStorage.getInstance()
            .reference.child("clients")

    val loginLoading: MutableLiveData<Boolean> = MutableLiveData()

    val progress: MutableLiveData<Int> = MutableLiveData()

    val userCurrent: MutableLiveData<Client> by lazy {
        MutableLiveData<Client>()
    }

    fun login(email: String, password: String, successCallback: (AuthResult?) -> Unit) {
        loginLoading.postValue(true)
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCanceledListener(this)
                .addOnFailureListener(this)
                .addOnSuccessListener { successCallback(it)  }
    }

    fun signUp(client: Client, imageUri: Uri, progressCallback: () -> Unit) {
        loginLoading.postValue(true)
        mAuth.createUserWithEmailAndPassword(client.email, client.password)
                .addOnCanceledListener(this)
                .addOnFailureListener(this)
                .addOnSuccessListener {
                    mClientsDatabaseReference.child(it.user.uid)
                            .setValue(client)
                            .addOnCanceledListener(this)
                            .addOnFailureListener(this)
                            .addOnSuccessListener { }
                    mClientsStorageReference.child(it.user.uid)
                            .putFile(imageUri)
                            .addOnProgressListener { }
                            .addOnSuccessListener { }
                }
    }

    override fun onCanceled() {
        loginLoading.postValue(false)
    }

    override fun onFailure(e: Exception) {
        loginLoading.postValue(false)
    }
}
