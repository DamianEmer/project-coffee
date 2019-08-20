package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable

class ProductCart(): BaseObservable() {

    constructor(
            idCart: String = "",
            idProduct: String = "",
            quantity: Int = 0,
            additionalNotes: String = "",
            priceTotal: Float = 0.00f
    ):this(){
        this.idCart = idCart
        this.idProduct = idProduct
        this.quantity = quantity
        this.additionalNotes = additionalNotes
        this.priceTotal = priceTotal
    }

    var idCart: String = ""
        set(value){
            field = value
        }

    var idProduct: String = ""
        set(value){
            field = value
        }

    var quantity: Int = 0
        set(value){
            field = value
        }

    var additionalNotes: String = ""
        set(value){
            field = value
        }

    var priceTotal: Float = 0.00f
        set(value){
            field = value
        }
}
