package com.dezc.coffeesaleapp.types

import android.util.Patterns

typealias Validator = (String) -> Boolean

typealias Validators = List<Validator>

val emailValidator: Validator = {
    Patterns.EMAIL_ADDRESS.matcher(it).matches()
}

val whatsAppNumberValidator: Validator = {
    it.length == 10
}

val passwordValidator = fun(passwordLength: Int): Validator = {
    it.length >= passwordLength
}

val codePostalValidator = fun(): Validator = {
    it.length == 5
}

val quantityValidator = fun(lim: Int): Validator = {
    it.length == lim
}