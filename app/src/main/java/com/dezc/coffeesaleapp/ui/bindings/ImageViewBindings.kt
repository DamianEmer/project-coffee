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
import jp.wasabeef.glide.transformations.BlurTransformation

object ImageViewBindings {

    @BindingAdapter("android:imageUrl")
    @JvmStatic
    fun ImageView.imageUrl(imageUrl: String?) {
        if (imageUrl != null) {
            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.cup)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(this)
        }
    }

    @BindingAdapter("android:blurImageUrl")
    @JvmStatic
    fun ImageView.loadBlurImageFromUrl(imageUrl: String?) {
        if (imageUrl != null)
            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.cup)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(10)))
                    .into(this)
    }

    @BindingAdapter(value = ["imageUrl", "glideTransformations"], requireAll = false)
    @JvmStatic
    fun ImageView.imageUrlWithTransformations(imageUrl: String?, glideTransformations: Array<Transformation<Bitmap>>?) {
        if (imageUrl != null) {
            var requestBuilder = Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.cup)
                    .transition(DrawableTransitionOptions.withCrossFade())
            if (glideTransformations != null && glideTransformations.isNotEmpty()) {
                requestBuilder = requestBuilder.apply(RequestOptions
                        .bitmapTransform(MultiTransformation<Bitmap>()))
            }
            requestBuilder.into(this)
        }
    }
}
