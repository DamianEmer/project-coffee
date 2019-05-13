package com.dezc.coffeesaleapp.functions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

fun Fragment.setSupportActionBar(toolbar: Toolbar) =
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
