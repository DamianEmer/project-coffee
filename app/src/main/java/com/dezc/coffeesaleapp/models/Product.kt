package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.dezc.coffeesaleapp.BR

@Entity(tableName = "product_table")
class Product() : BaseObservable() {

    constructor(id: Int = 0, name: String = "", price: Int = 0, image: String = "",
                description: String = "") : this() {
        this.id = id
        this.name = name
        this.price = price
        this.image = image
        this.description = description
    }

    constructor(id: Int = 0, name: String = "", price: Int = 0, image: String = "",
                description: String = "", quantity: Int = 0, total: Float = 0.0f) : this() {
        this.id = id
        this.name = name
        this.price = price
        this.image = image
        this.description = description
        this.quantity = quantity
        this.total = total
    }

    @get:Bindable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    var id: Int? = null
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
    var price: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.price)
        }

    @Ignore
    @get:Bindable
    var image: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.image)
        }

    @get:Bindable
    var description: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    var quantity: Int = 0

    @get:Bindable
    var total: Float = 0.0f
        set(value){
            field = value
        }
}
