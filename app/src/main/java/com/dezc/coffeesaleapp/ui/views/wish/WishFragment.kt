package com.dezc.coffeesaleapp.ui.views.wish

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentWishListBinding
import com.dezc.coffeesaleapp.viewmodels.WishViewModel
import kotlinx.android.synthetic.main.fragment_wish_list.*

class WishFragment : Fragment() {

    private lateinit var mAdapter: WishRecyclerViewAdapter

    private lateinit var mBinding: FragmentWishListBinding

    private lateinit var mWishViewModel: WishViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = FragmentWishListBinding.inflate(inflater)
        mBinding.context = this
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mAdapter = WishRecyclerViewAdapter()
        wish_list.adapter = mAdapter
        mWishViewModel = ViewModelProviders.of(activity!!).get(WishViewModel::class.java)
        mWishViewModel.allProducts.observe(this, Observer { products ->
            Log.i(WishFragment::class.simpleName, "allProducts -> Observer -> $products")
            products?.let {
                Log.i(WishFragment::class.simpleName,
                        "allProducts -> Observer -> Non Null -> ${it.map { product -> product.name }
                        .reduce { acc, s -> "$acc, $s" }}")
                mAdapter.replace(it)
            }
        })
    }

    fun onSale(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_mapsFragment)
    }
}
