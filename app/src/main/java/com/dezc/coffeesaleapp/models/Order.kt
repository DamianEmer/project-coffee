package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable

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

    var client: Client = Client()
        set(value) {
            field = value
        }

    var products: List<Product> = ArrayList<Product>()
        set(value) {
            field = value
        }

    var address: Address = Address()
        set(value) {
            field = value
        }

    var orderStatus: Boolean = false
        set(value) {
            field = value
        }

    var ingredient: String = ""
        set(value){
            field = value
        }

    var typePayment: String = ""
        set(value){
            field = value
        }

    var quantityPayment: String = ""
        set(value) {
            field = value
        }
}