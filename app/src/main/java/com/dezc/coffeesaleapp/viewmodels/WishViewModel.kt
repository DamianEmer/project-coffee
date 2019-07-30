package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dezc.coffeesaleapp.db.ProductRepository
import com.dezc.coffeesaleapp.db.ProductRoomDatabase
import com.dezc.coffeesaleapp.models.Client
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.models.ProductCart
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository = ProductRepository(ProductRoomDatabase.getDatabase(application).productDAO())

    private val insertLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val mDeleteLoading: MutableLiveData<Boolean> = MutableLiveData()

    val allProducts: LiveData<List<Product>>

    val deleteLoading: LiveData<Boolean> = mDeleteLoading

    // Firebase for shopping cart
    private val mShoppingCartDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("carts")

    // Boolean represents the product load to the cart
    val addCartLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        allProducts = repository.allProducts
    }

    fun insertWish(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        insertLoading.postValue(true)
        repository.insert(product)
        insertLoading.postValue(false)
    }

    fun deleteWish(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        product.id?.apply {
            mDeleteLoading.postValue(true)
            repository.delete(this)
            mDeleteLoading.postValue(false)
        }
    }

    private var idPushCart: String = "";

    fun addToCart(product: Product?, idClient: String, quantity: Int, additionalNotes: String, priceTotal: Float){
        if(idPushCart.length == 0){
            Log.i("WishViewModel -> ", "Se abre un carrito!!")
            val resultPush: DatabaseReference = mShoppingCartDatabaseReference.push();
            resultPush.setValue(product);
            idPushCart= resultPush.key.toString();
        }else{
            Log.i("WishViewModel - ", "Existe un carrito ya!!")
            mShoppingCartDatabaseReference.child(idPushCart).child("products").push().setValue(ProductCart(product!!.name, quantity, additionalNotes, priceTotal))
        }
    }

    fun onDeleteProductCart(idProduct: String){

    }

    fun getListProductsCart(){

    }

}
