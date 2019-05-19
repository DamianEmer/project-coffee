package com.dezc.coffeesaleapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView

import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.activities.DetailProductActivity
import com.dezc.coffeesaleapp.activities.dummy.DummyContent.DummyItem
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.components.ProductFragment
import com.dezc.coffeesaleapp.ui.utils.callback.OnListFragmentInteractionListener

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyProductRecyclerViewAdapter(
        private val mContext: ProductFragment,
        private val mValues: MutableList<Product>,
        private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mItem = mValues[position]
        holder.mIdView.text = item.id.toString()
        holder.mContentView.text = item.name

        /*holder.mView.setOnClickListener {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem)
                Toast.makeText(
                        mContext.context, "Elemento clickeado: " + holder.mItem!!, Toast.LENGTH_LONG).show()

                val intentDetailProduct = Intent(mContext.context, DetailProductActivity::class.java)
                intentDetailProduct.putExtra("ID Product", mValues[position].id)
                intentDetailProduct.putExtra("Content Product", mValues[position].content)
                mContext.startActivity(intentDetailProduct)

            }
        }*/
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    /*fun setItems(items: Collection<DummyItem>) {
        mValues.clear()
        mValues.addAll(items)
    }*/

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
