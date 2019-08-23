package com.dezc.coffeesaleapp.ui.views.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dezc.coffeesaleapp.R.layout.item_wish
import com.dezc.coffeesaleapp.databinding.ItemWishBinding
import com.dezc.coffeesaleapp.models.ProductCart
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.ui.utils.commons.adapters.DataBoundFirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class WishRecyclerViewAdapter(
        options: FirebaseRecyclerOptions<ProductCart>,
        private val mOnProductClickListener: OnProductClickListener?
) : DataBoundFirebaseRecyclerAdapter<ProductCart, ItemWishBinding>(options) {

    override fun createBinding(parent: ViewGroup): ItemWishBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), item_wish, parent, false)

    override fun bind(binding: ItemWishBinding, item: ProductCart, position: Int) {
        binding.listener = mOnProductClickListener
        binding.product = item
    }
}
