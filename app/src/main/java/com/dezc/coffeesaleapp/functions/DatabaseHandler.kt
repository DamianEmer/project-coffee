package com.dezc.coffeesaleapp.functions

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val DATABASE_NAME = "products_cart"
val TABLE_NAME = "products"
val COL_ID = "idx"
val COL_ID_PRODUCT = "id"
val COL_NAME = "name"
val COL_PRICE = "price"
val COL_DESCRIPTION = "description"

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =" CREATE TABLE " + TABLE_NAME + " ( "+
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ID_PRODUCT + " INTEGER, "+
                COL_NAME + " TEXT, " +
                COL_PRICE + " TEXT, "+
                COL_DESCRIPTION + " TEXT ); "

        db?.execSQL(createTable);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addProductToCart(){

    }

}