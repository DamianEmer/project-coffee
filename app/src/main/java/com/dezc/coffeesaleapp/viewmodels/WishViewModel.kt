package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dezc.coffeesaleapp.db.ProductRepository
import com.dezc.coffeesaleapp.db.ProductRoomDatabase
import com.dezc.coffeesaleapp.models.Address
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.models.ProductCart
import com.dezc.coffeesaleapp.ui.views.wish.WishFragment
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishViewModel(application: Application) : AndroidViewModel(application){

    private val repository: ProductRepository = ProductRepository(ProductRoomDatabase.getDatabase(application).productDAO())

    private val insertLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val mDeleteLoading: MutableLiveData<Boolean> = MutableLiveData()

    val address: MutableLiveData<Address> by lazy {
        MutableLiveData<Address>()
    }

    val typePayment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val effectiveQuantity: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }


    val additionalNotes: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val allProducts: LiveData<List<Product>>

    val deleteLoading: LiveData<Boolean> = mDeleteLoading

    private val mCurrentClient: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

    private val mClientsDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("clients")

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

    var currentCart: String = ""

    val currentUerId: MutableLiveData<String> = MutableLiveData()

    fun addToCart(product: Product?, idClient: String, quantity: Int, additionalNotes: String, priceTotal: Float){
        if(currentCart.length == 0){
            Log.i("WishViewModel -> ", "Se abre un carrito!!")
            val resultPush: DatabaseReference = mShoppingCartDatabaseReference.push()
            resultPush.child("idClient").setValue(mCurrentClient.uid)
            resultPush.child("status").setValue("pending")
            resultPush.child("products").push().setValue(ProductCart(product!!.id.toString(), quantity, additionalNotes, priceTotal))
            mClientsDatabaseReference.child(mCurrentClient.uid).child("currentCart").setValue(resultPush.key.toString())
            currentCart = resultPush.key.toString()
        }else{
            Log.i("WishViewModel - ", "Existe un carrito ya!!")
            mShoppingCartDatabaseReference.child(currentCart).child("products").push().setValue(ProductCart(product!!.id.toString(), quantity, additionalNotes, priceTotal))
        }
    }

    fun onDeleteProductCart(idProduct: String){

    }

    fun getListProductsCart(this_: WishFragment): FirebaseRecyclerOptions<ProductCart>{
        val ref = mShoppingCartDatabaseReference.child(currentCart).child("products")
        return FirebaseRecyclerOptions.Builder<ProductCart>()
                .setQuery(ref, ProductCart::class.java)
                .setLifecycleOwner(this_)
                .build()
    }

    fun existCart(): Boolean{
        return currentCart.isNotEmpty()
    }

    fun onOrder(){
        Log.i("WishFragment", "CurrentCart: "+currentCart);
        mShoppingCartDatabaseReference.child(currentCart).child("typePayment").setValue(typePayment.value)
        mShoppingCartDatabaseReference.child(currentCart).child("effectiveQuantity").setValue(effectiveQuantity.value)
        mShoppingCartDatabaseReference.child(currentCart).child("address").setValue(address.value)
    }

}
