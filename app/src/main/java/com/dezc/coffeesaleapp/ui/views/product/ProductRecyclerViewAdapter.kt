package com.dezc.coffeesaleapp.ui.views.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dezc.coffeesaleapp.R.layout.item_product
import com.dezc.coffeesaleapp.databinding.ItemProductBinding
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.ui.utils.commons.adapters.DataBoundFirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class ProductRecyclerViewAdapter(
        options: FirebaseRecyclerOptions<Product>,
        private val mListener: OnProductClickListener?
) : DataBoundFirebaseRecyclerAdapter<Product, ItemProductBinding>(options) {
    override fun createBinding(parent: ViewGroup): ItemProductBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), item_product, parent, false)

    override fun bind(binding: ItemProductBinding, position: Int, item: Product) {
        binding.product = item
        binding.listener = mListener
    }
}
