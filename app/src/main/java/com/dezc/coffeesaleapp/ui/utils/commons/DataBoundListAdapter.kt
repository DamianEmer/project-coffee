package com.dezc.coffeesaleapp.ui.utils.commons

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dezc.coffeesaleapp.functions.CollectionUtils.toArray

abstract class DataBoundListAdapter<T, V : ViewDataBinding> :
        RecyclerView.Adapter<DataBoundViewHolder<V>>() {

    lateinit var items: List<T>

    var dataVersion: Int = 0

    private val mDataAsyncUpdater = DataAsyncUpdater(this)

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int):
            DataBoundViewHolder<V> = DataBoundViewHolder(createBinding(parent))

    override fun getItemCount(): Int = if (::items.isInitialized) items.size else 0

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {
        bind(holder.binding, items[position], position)
        holder.binding.executePendingBindings()
    }

    @SuppressWarnings("StaticFieldLeak")
    fun replace(update: List<T>) {
        dataVersion++
        when {
            !::items.isInitialized -> {
                items = update
                notifyDataSetChanged()
            }
            update.isEmpty() -> {
                val oldSize: Int = items.size
                items = update
                notifyItemRangeRemoved(0, oldSize)
            }
            else -> mDataAsyncUpdater.execute(*toArray(update))
        }
    }

    abstract fun createBinding(parent: ViewGroup): V

    abstract fun bind(binding: V, item: T, position: Int)

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
}
