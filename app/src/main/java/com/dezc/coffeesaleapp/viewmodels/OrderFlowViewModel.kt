package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dezc.coffeesaleapp.db.ProductRepository
import com.dezc.coffeesaleapp.db.ProductRoomDatabase
import com.dezc.coffeesaleapp.models.Address
import com.dezc.coffeesaleapp.models.Client
import com.dezc.coffeesaleapp.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderFlowViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ProductRepository = ProductRepository(ProductRoomDatabase.getDatabase(application).productDAO())

    val allProducts: LiveData<List<Product>>

    val product: MutableLiveData<Product> = MutableLiveData()

    val client: MutableLiveData<Client> = MutableLiveData()

    val address: MutableLiveData<Address> by lazy {
        MutableLiveData<Address>()
    }

    val paymentType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val effectiveQuantity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val ingredient: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        allProducts = repository.allProducts
    }

    fun deleteAll()= viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }

}