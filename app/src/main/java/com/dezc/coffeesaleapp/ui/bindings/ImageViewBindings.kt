package com.dezc.coffeesaleapp.ui.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dezc.coffeesaleapp.R

object ImageViewBindings {

    @BindingAdapter("android:imageUrl")
    @JvmStatic
    fun ImageView.imageUrl(imageUrl: String?) {
        if (imageUrl != null)
            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.cup)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(this)
    }
}
