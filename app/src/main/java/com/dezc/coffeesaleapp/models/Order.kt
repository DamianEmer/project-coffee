package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dezc.coffeesaleapp.BR

class Order(): BaseObservable() {

    constructor(
            client: Client = Client(),
            products: List<Product>,
            address: Address = Address(),
            orderStatus: Boolean = false,
            ingredient: String = "",
            typePayment: String = "",
            quantityPayment: String = ""
        ):this(){
        this.client = client
        this.products = products
        this.address = address
        this.orderStatus = orderStatus
        this.ingredient = ingredient
        this.typePayment = typePayment
        this.quantityPayment = quantityPayment
    }

    @get:Bindable
    var client: Client = Client()
        set(value) {
            field = value
            notifyPropertyChanged(BR.client)
        }

    @get:Bindable
    var products: List<Product> = ArrayList<Product>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.products)
        }

    @get:Bindable
    var address: Address = Address()
        set(value) {
            field = value
            notifyPropertyChanged(BR.address)
        }

    @get:Bindable
    var orderStatus: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.orderStatus)
        }

    @get:Bindable
    var ingredient: String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.ingredient)
        }

    @get:Bindable
    var typePayment: String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.typePayment)
        }

    @get:Bindable
    var quantityPayment: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.quantityPayment)
        }
}
