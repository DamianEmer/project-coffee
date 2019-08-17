package com.dezc.coffeesaleapp.ui.views.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dezc.coffeesaleapp.R.layout.item_wish
import com.dezc.coffeesaleapp.databinding.ItemWishBinding
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.models.ProductCart
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.ui.utils.commons.adapters.DataBoundRecyclerAdapter
import com.dezc.coffeesaleapp.ui.utils.commons.adapters.DataBoundViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class WishRecyclerViewAdapter(
        options: FirebaseRecyclerOptions<ProductCart>,
        private val mOnProductClickListener: OnProductClickListener?
) : FirebaseRecyclerAdapter<ProductCart, DataBoundViewHolder<ItemWishBinding>>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<ItemWishBinding> =
        DataBoundViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), item_wish, parent, false))

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemWishBinding>, position: Int, product: ProductCart) {
        holder.binding.listener = mOnProductClickListener
        holder.binding.product = product
    }


}

