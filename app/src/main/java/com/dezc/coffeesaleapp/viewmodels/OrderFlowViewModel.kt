package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dezc.coffeesaleapp.db.ProductRepository
import com.dezc.coffeesaleapp.db.ProductRoomDatabase
import com.dezc.coffeesaleapp.models.Address
import com.dezc.coffeesaleapp.models.Product

class OrderFlowViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ProductRepository = ProductRepository(ProductRoomDatabase.getDatabase(application).productDAO())

    val allProducts: LiveData<List<Product>>

    val product: MutableLiveData<Product> = MutableLiveData()

    val address: MutableLiveData<Address> by lazy {
        MutableLiveData<Address>()
    }

    val paymentType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        allProducts = repository.allProducts
    }


}