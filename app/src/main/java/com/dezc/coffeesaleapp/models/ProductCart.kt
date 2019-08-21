package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dezc.coffeesaleapp.BR

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

    @get:Bindable
    var idCart: String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.idCart)
        }

    @get:Bindable
    var idProduct: String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.idProduct)
        }

    @get:Bindable
    var quantity: Int = 0
        set(value){
            field = value
            notifyPropertyChanged(BR.quantity)
        }

    @get:Bindable
    var additionalNotes: String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.additionalNotes)
        }

    @get:Bindable
    var priceTotal: Float = 0.00f
        set(value){
            field = value
            notifyPropertyChanged(BR.priceTotal)
        }
}
