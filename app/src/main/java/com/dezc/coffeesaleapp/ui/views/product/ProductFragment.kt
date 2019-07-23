package com.dezc.coffeesaleapp.ui.views.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductFragment : Fragment() {

    private val mProductsDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("products")

    private var mListener: OnProductClickListener? = null

    private var mAdapter: ProductRecyclerViewAdapter? = null

    private var dataset = arrayListOf("Bebidas", "Desayunos", "Cafes")

    private lateinit var mAdapterCategories: CategoryRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAdapterCategories = CategoryRecyclerViewAdapter()
        mAdapterCategories.replace(dataset)
        mAdapter = ProductRecyclerViewAdapter(FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(mProductsDatabaseReference, Product::class.java)
                .setLifecycleOwner(this).build(), mListener)
        categories_list.adapter = mAdapterCategories
        product_list.adapter = mAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnProductClickListener) {
            mListener = context
        } else {
            throw RuntimeException("$context must implement OnProductClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
}
