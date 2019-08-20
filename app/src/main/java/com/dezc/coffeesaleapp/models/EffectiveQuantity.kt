package com.dezc.coffeesaleapp.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dezc.coffeesaleapp.BR

class EffectiveQuantity: BaseObservable() {

    @get:Bindable
    var effectiveQuantity: String = ""
        set(value: String) {
            if(value != field)
                field = value
                notifyPropertyChanged(BR.effectiveQuantity)
        }

}