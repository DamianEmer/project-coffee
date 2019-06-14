package com.dezc.coffeesaleapp.ui.views.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dezc.coffeesaleapp.R.layout.item_wish
import com.dezc.coffeesaleapp.databinding.ItemWishBinding
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.ui.utils.commons.adapters.DataBoundRecyclerAdapter

class WishRecyclerViewAdapter(private val mOnProductClickListener: OnProductClickListener) : DataBoundRecyclerAdapter<Product, ItemWishBinding>() {
    override fun createBinding(parent: ViewGroup): ItemWishBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), item_wish, parent, false)

    override fun bind(binding: ItemWishBinding, item: Product, position: Int) {
        binding.product = item
        binding.onClickListener = mOnProductClickListener
    }

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem == newItem
}
