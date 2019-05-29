package com.dezc.coffeesaleapp.ui.views.product

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentDetailProductBinding
import com.dezc.coffeesaleapp.fragments.dummy.DummyContent
import com.dezc.coffeesaleapp.functions.DatabaseHandler
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.views.wish.WishFragment
import com.dezc.coffeesaleapp.viewmodels.ProductViewModel
import kotlinx.android.synthetic.main.fragment_detail_product.*

class DetailProductFragment : Fragment() {

    private val LOG_DETAIL_FRAGMENT = "DetailFragment";

    private lateinit var mDatabaseHandler: DatabaseHandler

    private lateinit var mViewModel: ProductViewModel

    private lateinit var mBinding: FragmentDetailProductBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentDetailProductBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)
        mViewModel.product.observe(this, Observer { setData(it) })

        val context: Context = this.context?: return null!!

        mDatabaseHandler = DatabaseHandler(context)
    }

    private fun setData(product: Product) {
        mBinding.product = product
        Glide.with(this).load(R.drawable.coffee).into(image_product)
    }

    fun addCart(view: View) {

        Log.i(LOG_DETAIL_FRAGMENT, "${mBinding.product.toString()}");

        mViewModel.product.observe(this, Observer {
            mDatabaseHandler.addProductToCart(it);
        })

        Toast.makeText(context, "Agregado al carrito", Toast.LENGTH_SHORT).show()
    }

}
