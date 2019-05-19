package com.dezc.coffeesaleapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.models.Product

/**
 * [RecyclerView.Adapter] that can display a [Product]
 */
class MyWishRecyclerViewAdapter(items: List<Product>) : RecyclerView.Adapter<MyWishRecyclerViewAdapter.ViewHolder>() {

    init {
        mValues = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_wish, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mItem = mValues[position]
        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = item.name

        holder.mView.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView
        val mContentView: TextView
        var mItem: Product? = null

        init {
            mIdView = mView.findViewById<View>(R.id.item_number) as TextView
            mContentView = mView.findViewById<View>(R.id.content) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    companion object {

        private lateinit var mValues: List<Product>
    }
}
