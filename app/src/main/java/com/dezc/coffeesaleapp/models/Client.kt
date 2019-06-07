package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dezc.coffeesaleapp.BR
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Client() : BaseObservable() {

    @get:Bindable
    var clientId: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.clientId)
        }

    @get:Bindable
    var name: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var lastName: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }

    @get:Bindable
    var secondLastName: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.secondLastName)
        }

    @get:Bindable
    var numberWhatsApp: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.numberWhatsApp)
        }

    @get:Bindable
    var email: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @Exclude
    var password: String? = null

    @get:Bindable
    var profilePhoto: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.profilePhoto)
        }

    constructor(name: String, lastName: String, secondLastName: String, numberWhatsApp: String,
                email: String) : this() {
        this.name = name
        this.lastName = lastName
        this.secondLastName = secondLastName
        this.numberWhatsApp = numberWhatsApp
        this.email = email
    }

    constructor(name: String, lastName: String, secondLastName: String, numberWhatsApp: String,
                email: String, password: String, profilePhoto: String) : this() {
        this.name = name
        this.lastName = lastName
        this.secondLastName = secondLastName
        this.numberWhatsApp = numberWhatsApp
        this.email = email
        this.password = password
        this.profilePhoto = profilePhoto
    }

    constructor(name: String, lastName: String, secondLastName: String, numberWhatsApp: String,
                email: String, password: String) : this() {
        this.name = name
        this.lastName = lastName
        this.secondLastName = secondLastName
        this.numberWhatsApp = numberWhatsApp
        this.email = email
        this.password = password
    }
}
