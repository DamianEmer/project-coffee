package com.dezc.coffeesaleapp.functions

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.dezc.coffeesaleapp.models.Product


val DATABASE_NAME = "products_cart"
val TABLE_NAME = "products"
val COL_ID = "idx"
val COL_ID_PRODUCT = "id"
val COL_NAME = "name"
val COL_PRICE = "price"
val COL_DESCRIPTION = "description"

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = " CREATE TABLE " + TABLE_NAME + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ID_PRODUCT + " INTEGER, " +
                COL_NAME + " TEXT, " +
                COL_PRICE + " TEXT, " +
                COL_DESCRIPTION + " TEXT ); "

        db?.execSQL(createTable);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addProductToCart(product: Product) : Long{
        val db = this.writableDatabase
        val valueContent = ContentValues()
        valueContent.put(COL_ID_PRODUCT, product.id)
        valueContent.put(COL_NAME, product.name)
        valueContent.put(COL_PRICE, product.price)
        valueContent.put(COL_DESCRIPTION, product.description)
        val result: Long = db.insert(TABLE_NAME, null, valueContent)
        db.close()

        return result;
    }

    fun getAllProducts(): MutableList<Product>{
        Log.i("DatabaseHandler:: ", "Obtener todos los productos")
        val products: MutableList<Product> = ArrayList()
        val db = this.readableDatabase
        val query: String = "SELECT * FROM ${TABLE_NAME}"
        val cursor: Cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            do{
                var product = Product()
                product.id = cursor.getInt(cursor.getColumnIndex(COL_ID_PRODUCT))
                product.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                product.price = cursor.getInt(cursor.getColumnIndex(COL_PRICE))
                product.description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION))
                Log.i("DatabaseHandler:: ", "producto: ${product.name}")
                products.add(product)
            }while (cursor.moveToNext())
        }
        db.close()
        return products
    }

}