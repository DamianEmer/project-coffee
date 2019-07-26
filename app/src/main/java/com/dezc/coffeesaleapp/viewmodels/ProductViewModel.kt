package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dezc.coffeesaleapp.models.Product
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val mDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("products")

    private val mCategory: MutableLiveData<String> = MutableLiveData()

    val product = MutableLiveData<Product>()

    val products: LiveData<List<Product>> = Transformations
            .switchMap<String, List<Product>>(mCategory) { getProductsByCategory(it) }

    fun setCategory(category: String) = mCategory.postValue(category)

    private fun getProductsByCategory(category: String): LiveData<List<Product>> {
        Log.i(ProductViewModel::class.simpleName, "Category $category")
        val products: MutableLiveData<List<Product>> = MutableLiveData()
        mDatabaseReference.orderByChild("category").startAt(category)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Log.e(ProductViewModel::class.simpleName, "onCancelled", p0.toException())
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        dataSnapshot.children.forEach { data -> Log.i(ProductViewModel::class.simpleName, "Category $data") }
                        products.postValue(dataSnapshot.children
                                .mapNotNull { data -> data.getValue(Product::class.java) })
                    }
                })
        return products
    }
}
