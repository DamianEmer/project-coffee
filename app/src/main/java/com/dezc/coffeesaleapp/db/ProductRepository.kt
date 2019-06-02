package com.dezc.coffeesaleapp.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.dezc.coffeesaleapp.db.daos.ProductDAO
import com.dezc.coffeesaleapp.models.Product

class ProductRepository(private val productDAO: ProductDAO) {

    val allProducts: LiveData<List<Product>> = productDAO.getAllProducts()

    @WorkerThread
    suspend fun insert(product: Product) = productDAO.insertProduct(product)
}
