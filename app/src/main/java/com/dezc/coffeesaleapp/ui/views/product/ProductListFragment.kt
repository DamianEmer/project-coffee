package com.dezc.coffeesaleapp.ui.views.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dezc.coffeesaleapp.databinding.FragmentProductListBinding
import com.dezc.coffeesaleapp.models.Category
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnClickListener
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.viewmodels.ProductViewModel
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class ProductListFragment : Fragment(), OnClickListener<Category> {

    private lateinit var mBinding: FragmentProductListBinding

    private var mListener: OnProductClickListener? = null

    private lateinit var mProductRecyclerViewAdapter: ProductRecyclerViewAdapter

    private lateinit var mCategoryRecyclerAdapter: CategoryRecyclerViewAdapter

    private lateinit var mProductViewModel: ProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = FragmentProductListBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mProductViewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)

        mCategoryRecyclerAdapter = CategoryRecyclerViewAdapter(this, FirebaseRecyclerOptions
                .Builder<Category>()
                .setQuery(FirebaseDatabase.getInstance().reference.child("categories"), Category::class.java)
                .setLifecycleOwner(this)
                .build())

        mProductRecyclerViewAdapter = ProductRecyclerViewAdapter(mListener)
        mProductViewModel.products.observe(this, Observer { mProductRecyclerViewAdapter.replace(it) })
        mProductViewModel.currentCategory.observe(this, Observer {
            mBinding.currentCategory = it
        })

        mBinding.categoryAdapter = mCategoryRecyclerAdapter
        mBinding.productAdapter = mProductRecyclerViewAdapter
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

    override fun onClick(p0: Category) {
        mProductViewModel.setCategory(p0)
    }
}
