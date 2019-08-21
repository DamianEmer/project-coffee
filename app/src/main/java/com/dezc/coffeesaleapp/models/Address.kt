package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import com.dezc.coffeesaleapp.BR

class Address(): BaseObservable() {

    constructor(street: String = "",
                codePostal: Int = 0,
                outdoorNumber: Int = 0,
                interiorNumber: Int = 0,
                suburb: String = "",
                city: String = "",
                town: String = ""):this(){
        this.street = street
        this.codePostal = codePostal
        this.outdoorNumber = outdoorNumber
        this.interiorNumber = interiorNumber
        this.suburb = suburb
        this.city = city
        this.town = town
    }

    var street: String = ""
        set(value) {
            field = value
            //notifyPropertyChanged(BR.street)
        }

    var codePostal: Int = 0
        set(value) {
            field = value
            //notifyPropertyChanged(BR.codePostal)
        }

    var outdoorNumber: Int = 0
        set(value) {
            field = value
            //notifyPropertyChanged(BR.outdoorNumber)
        }

    var interiorNumber: Int = 0
        set(value) {
            field = value
            //notifyPropertyChanged(BR.interiorNumber)
        }

    var suburb: String = ""
        set(value) {
            field = value
            //notifyPropertyChanged(BR.suburb)
        }

    var city: String = ""
        set(value) {
            field = value
            //notifyPropertyChanged(BR.city)
        }

    var town: String = ""
        set(value){
            field = value
            //notifyPropertyChanged(BR.town)
        }
}