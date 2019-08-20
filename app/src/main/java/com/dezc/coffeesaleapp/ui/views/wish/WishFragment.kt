package com.dezc.coffeesaleapp.ui.views.wish

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentWishListBinding
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.models.ProductCart
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener
import com.dezc.coffeesaleapp.viewmodels.WishViewModel
import kotlinx.android.synthetic.main.fragment_wish_list.*

class WishFragment : Fragment() {

    private var mListener: OnProductClickListener? = null

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
        mWishViewModel = ViewModelProviders.of(activity!!).get(WishViewModel::class.java)
        mAdapter = WishRecyclerViewAdapter(
                mWishViewModel.getListProductsCart(this),
                mListener)
        wish_list.adapter = mAdapter
    }

    fun onSale(view: View) {
        if(mWishViewModel.existCart())
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_mapsFragment)
        else {
            Toast.makeText(view.context, "No hay ningun producto en la lista", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnProductClickListener){
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

