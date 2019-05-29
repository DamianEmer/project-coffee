package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dezc.coffeesaleapp.models.Client
import com.dezc.coffeesaleapp.ui.utils.callbacks.CompleteCallback
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

    val signUpProgress: MutableLiveData<Int> = MutableLiveData()

    fun login(email: String, password: String, successCallback: (AuthResult?) -> Unit) {
        loginLoading.postValue(true)
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCanceledListener(this)
                .addOnFailureListener(this)
                .addOnSuccessListener { successCallback(it) }
    }

    fun signUp(client: Client, imageUri: Uri, completeCallback: CompleteCallback<Void>) {
        loginLoading.postValue(true)
        mAuth.createUserWithEmailAndPassword(client.email, client.password)
                .addOnCanceledListener(this)
                .addOnFailureListener(this)
                .addOnSuccessListener { authResult ->
                    signUpProgress.postValue(30)
                    val storageReference = mClientsStorageReference.child(authResult.user.uid)
                    storageReference.putFile(imageUri)
                            .addOnProgressListener { taskSnapshot ->
                                signUpProgress.postValue(signUpProgress
                                        .value?.plus((30 * (100.0 * taskSnapshot.bytesTransferred) /
                                        taskSnapshot.totalByteCount).toInt()))
                            }
                            .continueWithTask { task ->
                                if (!task.isSuccessful) {
                                    throw task.exception!!
                                }
                                storageReference.downloadUrl
                            }
                            .addOnSuccessListener { uriTask ->
                                client.profilePhoto = uriTask.toString()
                                mClientsDatabaseReference.child(authResult.user.uid)
                                        .setValue(client)
                                        .addOnCanceledListener(this)
                                        .addOnFailureListener(this)
                                        .addOnSuccessListener {
                                            signUpProgress.postValue(signUpProgress
                                                    .value?.plus(30) ?: 30)
                                            completeCallback(it)
                                        }
                            }
                }
    }

    override fun onCanceled() {
        loginLoading.postValue(false)
    }

    override fun onFailure(e: Exception) {
        loginLoading.postValue(false)
    }
}
