package com.dezc.coffeesaleapp.functions

import android.app.Activity
import android.content.res.Resources
import androidx.annotation.IntegerRes

fun Activity.getInteger(@IntegerRes idRes: Int, defaultInt: Int = 0): Int = try {
    applicationContext.resources.getInteger(idRes)
} catch (e: Resources.NotFoundException) {
    defaultInt
}
