package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dezc.coffeesaleapp.BR

class Category() : BaseObservable() {

    @get:Bindable
    var id: String = ""
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
    var image: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.image)
        }

    @get:Bindable
    var categoryImage: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.categoryImage)
        }

    constructor(id: String, name: String, image: String, categoryImage: String) : this() {
        this.id = id
        this.name = name
        this.image = image
        this.categoryImage = categoryImage
    }

}
