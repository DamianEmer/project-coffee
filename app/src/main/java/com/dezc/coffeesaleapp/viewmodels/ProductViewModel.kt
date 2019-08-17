package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dezc.coffeesaleapp.models.Category
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.models.ProductCart
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val mDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("products")

    private val mCategory: MutableLiveData<Category> = MutableLiveData()
    val currentCategory: LiveData<Category> = mCategory

    val product = MutableLiveData<Product>()

    val productSelect: MutableLiveData<ProductCart> = MutableLiveData()

    val getProductById: LiveData<Product> = Transformations
            .switchMap<ProductCart, Product>(productSelect) { getProductByIdTransformation(it.idProduct)}

    val products: LiveData<List<Product>> = Transformations
            .switchMap<Category, List<Product>>(mCategory) { getProductsByCategory(it.id) }

    fun setCategory(category: Category) = mCategory.postValue(category)

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

    private fun getProductByIdTransformation(productId: String): LiveData<Product>{
        Log.i(ProductViewModel::class.simpleName, "productID $productId")
        val product: MutableLiveData<Product> = MutableLiveData()

        mDatabaseReference.child(productId).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e(ProductViewModel::class.simpleName, "OnCancelled", p0.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i(ProductViewModel::class.simpleName, "value "+dataSnapshot.value)
                product.postValue(dataSnapshot.getValue(Product::class.java))
            }
        })
        return product
    }
}
