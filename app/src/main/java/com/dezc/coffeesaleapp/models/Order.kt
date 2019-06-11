package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable

class Order(): BaseObservable() {

    constructor(
            client: Client = Client(),
            products: List<Product>,
            address: Address = Address(),
            orderStatus: Boolean = false
        ):this(){
        this.client = client
        this.products = products
        this.address = address
        this.orderStatus = orderStatus
    }

    var client: Client = Client()
        set(value) {
            field = value
        }

    var nameClient: String = ""
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
}