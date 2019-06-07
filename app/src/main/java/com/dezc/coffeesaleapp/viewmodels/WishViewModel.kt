package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dezc.coffeesaleapp.db.ProductRepository
import com.dezc.coffeesaleapp.db.ProductRoomDatabase
import com.dezc.coffeesaleapp.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository = ProductRepository(ProductRoomDatabase.getDatabase(application).productDAO())

    private val insertLoading: MutableLiveData<Boolean> = MutableLiveData()

    val allProducts: LiveData<List<Product>>

    init {
        allProducts = repository.allProducts
    }

    fun insertWish(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        insertLoading.postValue(true)
        repository.insert(product)
        insertLoading.postValue(false)
    }
}
