package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dezc.coffeesaleapp.models.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val mDatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("products")

    private val mCategory: MutableLiveData<String> = MutableLiveData()

    val products: LiveData<List<Product>> = Transformations.switchMap(mCategory) {
        val products: MutableLiveData<List<Product>> = MutableLiveData()
        mDatabaseReference.orderByChild("category").startAt(it).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                products.postValue(dataSnapshot.children
                        .filterNotNull()
                        .map { data -> data.getValue(Product::class.java) }
                        .filterNotNull())
            }
        })
        return@switchMap products
    }

    fun setCategory(category: String) = mCategory.postValue(category)

    val product = MutableLiveData<Product>()
}
