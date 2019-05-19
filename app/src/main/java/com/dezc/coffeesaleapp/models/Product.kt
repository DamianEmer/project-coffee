package com.dezc.coffeesaleapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
        var id: Int = 0,
        var name: String = "",
        var price: String = "",
        var description: String = "") : Parcelable {

}
