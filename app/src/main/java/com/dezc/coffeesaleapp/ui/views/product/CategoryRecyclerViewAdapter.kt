package com.dezc.coffeesaleapp.ui.views.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dezc.coffeesaleapp.R.layout.item_category
import com.dezc.coffeesaleapp.databinding.ItemCategoryBinding
import com.dezc.coffeesaleapp.ui.utils.commons.adapters.DataBoundRecyclerAdapter

class CategoryRecyclerViewAdapter : DataBoundRecyclerAdapter<String, ItemCategoryBinding>() {
    override fun createBinding(parent: ViewGroup): ItemCategoryBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), item_category, parent, false)

    override fun bind(binding: ItemCategoryBinding, item: String, position: Int) {
        binding.category = item
    }

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}
