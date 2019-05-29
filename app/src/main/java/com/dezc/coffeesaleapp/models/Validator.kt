package com.dezc.coffeesaleapp.models

import java.util.regex.*

typealias Validator = (String) -> Boolean

val emailValidator: Validator = {
    val emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$"
    val pattern = Pattern.compile(emailPattern)
    pattern.matcher(it).matches()
}

val whatsAppNumberValidator: Validator = {
    it.length == 10
}
