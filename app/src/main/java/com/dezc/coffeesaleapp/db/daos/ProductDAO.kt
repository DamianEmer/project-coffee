package com.dezc.coffeesaleapp.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dezc.coffeesaleapp.models.Product

@Dao interface ProductDAO {

    @Query("SELECT * from product_table ORDER BY product_id")
    fun getAllProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("DELETE from product_table")
    fun deleteAll()
}
