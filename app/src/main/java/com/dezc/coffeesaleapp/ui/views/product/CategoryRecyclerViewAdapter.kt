package com.dezc.coffeesaleapp.ui.views.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dezc.coffeesaleapp.R.layout.item_category
import com.dezc.coffeesaleapp.databinding.ItemCategoryBinding
import com.dezc.coffeesaleapp.models.Category
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnClickListener
import com.dezc.coffeesaleapp.ui.utils.commons.adapters.DataBoundFirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CategoryRecyclerViewAdapter(private var onClickListener: OnClickListener<Category>,
                                  options: FirebaseRecyclerOptions<Category>) : DataBoundFirebaseRecyclerAdapter<Category, ItemCategoryBinding>(options) {

    override fun createBinding(parent: ViewGroup): ItemCategoryBinding {
        val item = DataBindingUtil.inflate<ItemCategoryBinding>(LayoutInflater.from(parent.context), item_category, parent, false)
        item.root.layoutParams = item.root.layoutParams.apply {
            width = (parent.width * 0.3).toInt()
        }
        return item
    }

    override fun bind(binding: ItemCategoryBinding, item: Category, position: Int) {
        binding.category = item
        binding.onClickListener = onClickListener
    }
}
