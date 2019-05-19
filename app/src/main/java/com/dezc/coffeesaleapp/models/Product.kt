package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dezc.coffeesaleapp.BR

class Product() : BaseObservable() {

    constructor(id: Int, name: String, price: String, description: String) : this() {
        this.id = id
        this.name = name
        this.price = price
        this.description = description
    }

    @get:Bindable
    var id: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var price: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.price)
        }

    @get:Bindable
    var description: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }
}
