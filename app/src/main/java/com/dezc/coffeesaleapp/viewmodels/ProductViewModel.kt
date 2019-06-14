package com.dezc.coffeesaleapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dezc.coffeesaleapp.models.Product

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    val product: MutableLiveData<Product> = MutableLiveData()
}
