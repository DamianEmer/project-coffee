package com.dezc.coffeesaleapp.functions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.dezc.coffeesaleapp.types.Validator

fun EditText.addEditTextValidators(validators: List<Validator>, errorMessage: String) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (!validators.all { it(s.toString()) }) {
                error = errorMessage
                requestFocus()
            }
        }
    })
}
