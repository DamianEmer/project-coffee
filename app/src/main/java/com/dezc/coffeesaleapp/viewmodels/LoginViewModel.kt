package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dezc.coffeesaleapp.models.Client
import com.dezc.coffeesaleapp.models.FormErrors
import com.dezc.coffeesaleapp.ui.utils.callbacks.CompleteCallback
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
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

    private val mLoginErrors = MutableLiveData<FormErrors>()

    val loginLoading: MutableLiveData<Boolean> = MutableLiveData()

    val signUpProgress: MutableLiveData<Int> = MutableLiveData()

    val loginErrors: LiveData<FormErrors> = mLoginErrors

    fun login(email: String, password: String, successCallback: (AuthResult?) -> Unit) {
        loginLoading.postValue(true)
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCanceledListener(this)
                .addOnFailureListener {
                    mLoginErrors.postValue(when (it) {
                        is FirebaseAuthInvalidUserException ->
                            FormErrors("User does not exist", "email") // TODO User does not exist
                        is FirebaseAuthInvalidCredentialsException ->
                            FormErrors("Invalid password", "password") // TODO Password invalid or User dos not have a password
                        else -> null
                    })
                    loginLoading.postValue(false)
                }
                .addOnSuccessListener { successCallback(it) }
    }

    fun signUp(client: Client, imageUri: Uri =
            Uri.parse("https://firebasestorage.googleapis.com/v0/b/coffee-sale.appspot.com/o/profile_photo.png?alt=media&token=b08d65e6-1cfd-4a4a-80a7-576c96de22de"), completeCallback: CompleteCallback<Void>) {
        loginLoading.postValue(true)
        mAuth.createUserWithEmailAndPassword(client.email!!, client.password!!)
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
