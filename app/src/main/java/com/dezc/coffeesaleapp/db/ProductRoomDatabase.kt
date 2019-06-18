package com.dezc.coffeesaleapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dezc.coffeesaleapp.db.daos.ProductDAO
import com.dezc.coffeesaleapp.models.Product

@Database(entities = [Product::class], version = 3)
abstract class ProductRoomDatabase : RoomDatabase() {

    abstract fun productDAO(): ProductDAO

    companion object {
        @Volatile
        private var INSTANCE: ProductRoomDatabase? = null

        fun getDatabase(context: Context) = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(context.applicationContext,
                    ProductRoomDatabase::class.java,
                    "Product_database")
                    .fallbackToDestructiveMigration()
                    .build()
            INSTANCE = instance
            instance
        }
    }
}
