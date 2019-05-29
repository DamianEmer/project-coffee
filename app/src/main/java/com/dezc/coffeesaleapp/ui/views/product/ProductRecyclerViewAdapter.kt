package com.dezc.coffeesaleapp.ui.views.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dezc.coffeesaleapp.R.layout.item_product
import com.dezc.coffeesaleapp.databinding.ItemProductBinding
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.ui.utils.commons.DataBoundViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ProductRecyclerViewAdapter(
        options: FirebaseRecyclerOptions<Product>,
        private val mListener: OnProductClickListener?
) : FirebaseRecyclerAdapter<Product, DataBoundViewHolder<ItemProductBinding>>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<ItemProductBinding> =
            DataBoundViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), item_product, parent, false))

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemProductBinding>, position: Int, product: Product) {
        holder.binding.product = product
        holder.binding.listener = mListener
        holder.binding.executePendingBindings()
    }
}
