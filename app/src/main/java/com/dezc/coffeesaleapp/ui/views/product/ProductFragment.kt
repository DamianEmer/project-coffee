package com.dezc.coffeesaleapp.ui.views.product

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.viewmodels.ProductViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductFragment : Fragment() {

    private var mListener: OnProductClickListener? = null

    private lateinit var mAdapter: ProductRecyclerViewAdapter

    private var dataset = arrayListOf("Bebidas", "Desayunos", "coffee")

    private lateinit var mAdapterCategories: CategoryRecyclerViewAdapter

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mProductViewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)

        mAdapterCategories = CategoryRecyclerViewAdapter()
        mAdapterCategories.replace(dataset)

        mAdapter = ProductRecyclerViewAdapter(mListener)
        mProductViewModel.products.observe(this, Observer { mAdapter.replace(it) })

        categories_list.adapter = mAdapterCategories
        product_list.adapter = mAdapter

        mProductViewModel.setCategory("coffee")
        FirebaseDatabase.getInstance().reference.child("products").orderByChild("category")
                .startAt("coffee").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Log.e(ProductFragment::class.simpleName, "onCancelled", p0.toException())
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        Log.i(ProductFragment::class.simpleName, "onDataChange ${dataSnapshot.value}")
                        dataSnapshot.children.forEach { data -> Log.i(ProductFragment::class.simpleName, "Category $data") }
                    }
                })
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
