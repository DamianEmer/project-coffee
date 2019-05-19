package com.dezc.coffeesaleapp.ui.views.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView

import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.activities.dummy.DummyContent.DummyItem
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.utils.callback.OnProductClickListener
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnProductClickListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ProductRecyclerViewAdapter(
        private val mContext: ProductFragment,
        options: FirebaseRecyclerOptions<Product>,
        private val mListener: OnProductClickListener?
) : FirebaseRecyclerAdapter<Product, ProductRecyclerViewAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, product: Product) {

        holder.mItem = product
        holder.mIdView.text = product.id.toString()
        holder.mContentView.text = product.name

        holder.mView.setOnClickListener {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onProductClickListener(holder.mItem)
                Toast.makeText(
                        mContext.context, "Elemento clickeado: " + holder.mItem!!, Toast.LENGTH_LONG).show()
            }
        }
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
}
