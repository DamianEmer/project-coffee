package com.dezc.coffeesaleapp.ui.views.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentDetailProductBinding
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.viewmodels.ProductViewModel
import com.dezc.coffeesaleapp.viewmodels.WishViewModel
import kotlinx.android.synthetic.main.fragment_detail_product.*

class DetailProductFragment : Fragment() {

    private lateinit var mProductViewModel: ProductViewModel

    private lateinit var mWishViewModel: WishViewModel

    private lateinit var mBinding: FragmentDetailProductBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentDetailProductBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mProductViewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)
        mWishViewModel = ViewModelProviders.of(activity!!).get(WishViewModel::class.java)

        mProductViewModel.product.observe(this, Observer { setData(it) })
    }

    private fun setData(product: Product) {
        mBinding.product = product
        Glide.with(this).load(R.drawable.coffee).into(image_product)
    }

    fun addCart(view: View) {
        mProductViewModel.product.observe(this, Observer {
            mWishViewModel.insertWish(it)
            Toast.makeText(view.context, "Agregado al carrito", Toast.LENGTH_SHORT).show()
        })
    }
}
