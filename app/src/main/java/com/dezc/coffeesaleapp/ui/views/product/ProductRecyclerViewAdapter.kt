package com.dezc.coffeesaleapp.ui.views.product

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dezc.coffeesaleapp.R.layout.item_product
import com.dezc.coffeesaleapp.databinding.ItemProductBinding
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.ui.utils.commons.adapters.DataBoundRecyclerAdapter

class ProductRecyclerViewAdapter(private val mListener: OnProductClickListener?) :
        DataBoundRecyclerAdapter<Product, ItemProductBinding>() {

    override fun createBinding(parent: ViewGroup): ItemProductBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), item_product, parent, false)

    override fun bind(binding: ItemProductBinding, item: Product, position: Int) {
        Log.i(ProductRecyclerViewAdapter::class.simpleName, item.name)
        binding.product = item
        binding.listener = mListener
    }

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean = oldItem.description == newItem.description
}
