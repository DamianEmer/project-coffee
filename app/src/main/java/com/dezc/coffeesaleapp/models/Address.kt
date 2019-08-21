package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dezc.coffeesaleapp.BR

class Address() : BaseObservable() {

    constructor(street: String = "",
                codePostal: Int = 0,
                outdoorNumber: Int = 0,
                interiorNumber: Int = 0,
                suburb: String = "",
                city: String = "",
                town: String = "") : this() {
        this.street = street
        this.codePostal = codePostal
        this.outdoorNumber = outdoorNumber
        this.interiorNumber = interiorNumber
        this.suburb = suburb
        this.city = city
        this.town = town
    }

    @get:Bindable
    var street: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.street)
        }

    @get:Bindable
    var codePostal: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.codePostal)
        }

    @get:Bindable
    var outdoorNumber: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.outdoorNumber)
        }

    @get:Bindable
    var interiorNumber: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.interiorNumber)
        }

    @get:Bindable
    var suburb: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.suburb)
        }

    @get:Bindable
    var city: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.city)
        }

    @get:Bindable
    var town: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.town)
        }
}
