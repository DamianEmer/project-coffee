package com.dezc.coffeesaleapp.ui.utils.commons.adapters

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

abstract class DataBoundFirebaseRecyclerAdapter<T, V : ViewDataBinding>(options: FirebaseRecyclerOptions<T>)
    : FirebaseRecyclerAdapter<T, DataBoundViewHolder<V>>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<V>
            = DataBoundViewHolder(createBinding(parent))

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int, item: T) {
        bind(holder.binding, position, item)
        holder.binding.executePendingBindings()
    }

    abstract fun createBinding(parent: ViewGroup): V

    abstract fun bind(binding: V, position: Int, item: T)
}
