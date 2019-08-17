package com.dezc.coffeesaleapp.ui.bindings

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.dezc.coffeesaleapp.R

object ImageViewBindings {

    @BindingAdapter(value = ["android:imageUrl", "android:transformations"], requireAll = false)
    @JvmStatic
    fun ImageView.imageUrl(imageUrl: String?, transformations: Array<Transformation<Bitmap>>?) {
        if (imageUrl != null) {
            var requestBuilder = Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.cup)
                    .transition(DrawableTransitionOptions.withCrossFade())
            if (transformations != null && transformations.isNotEmpty()) {
                requestBuilder = requestBuilder.apply(RequestOptions
                        .bitmapTransform(MultiTransformation<Bitmap>()))
            }
            requestBuilder.into(this)
        }
    }
}
