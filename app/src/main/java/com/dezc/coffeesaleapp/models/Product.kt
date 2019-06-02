package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dezc.coffeesaleapp.BR

@Entity(tableName = "product_table")
class Product() : BaseObservable() {

    constructor(id: Int = 0, name: String = "", price: Int = 0, description: String = "") : this() {
        this.id = id
        this.name = name
        this.price = price
        this.description = description
    }

    @get:Bindable
    @PrimaryKey
    @ColumnInfo(name = "product_id")
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
    var price: Int = 0
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
