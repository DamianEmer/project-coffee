package com.dezc.coffeesaleapp.ui.views.product

import android.app.AlertDialog
import android.app.Dialog
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
import com.bumptech.glide.Glide
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentDetailProductBinding
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.models.ProductCart
import com.dezc.coffeesaleapp.viewmodels.ProductViewModel
import com.dezc.coffeesaleapp.viewmodels.WishViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.dialog_additional.view.*
import kotlinx.android.synthetic.main.dialog_confirm.view.*
import kotlinx.android.synthetic.main.fragment_detail_product.*

class DetailProductFragment : Fragment() {

    private lateinit var mProductViewModel: ProductViewModel

    private lateinit var mWishViewModel: WishViewModel

    private lateinit var mBinding: FragmentDetailProductBinding

    private val mUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentDetailProductBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mProductViewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)
        mWishViewModel = ViewModelProviders.of(activity!!).get(WishViewModel::class.java)
        mProductViewModel.product.observe(this, Observer { setData(it) })
        mProductViewModel.productSelect.observe(this, Observer { setDataEditable(it) })
    }

    override fun onPause() {
        super.onPause()
        Log.i("DetailProductFragment", "----> onPauseActive")
        mProductViewModel.productSelect.removeObserver { }
    }

    private fun setDataEditable(productCart: ProductCart) {
        Log.i(DetailProductFragment::class.simpleName, "productCart: " + productCart.idProduct)
        mBinding.productCart = productCart
    }

    private fun setData(product: Product) {
        mBinding.product = product
        Glide.with(this).load(R.drawable.coffee).override(800, 200).into(image_product)
    }

    fun addCart(view: View) {
        if (this.quantityCount >= 1) {
            val priceTotal: Float = java.lang.Float.parseFloat((mBinding.product!!.price * this.quantityCount).toString())
            this.onDialogConfirm(view, priceTotal)
        } else {
            Toast.makeText(view.context, "Ingresa una cantidad", Toast.LENGTH_LONG).show()
        }
    }

    var additionalNotes: String = "";

    fun onCreateModal(view: View) {
        val inflater = layoutInflater;
        val dialogLayout = inflater.inflate(R.layout.dialog_additional, null);
        val builder = AlertDialog.Builder(context!!);
        builder.setTitle("Notas adicionales");
        builder.setView(dialogLayout);
        builder.setCancelable(true);
        builder.setPositiveButton("OK") { dialog, which ->
            this.additionalNotes = dialogLayout.text_ingredient.text.toString().trim()
        }.setNegativeButton("Cancel") { dialog, which ->
        }
        builder.show()
    }

    var quantityCount: Int = 0;

    fun onIncrementCounter(view: View) {
        this.quantityCount++;
        this.text_layout_quantity.text = this.quantityCount.toString();
        if (this.quantityCount > 0) this.btn_decrement.isClickable = true;
    }

    fun onDecrementCounter(view: View) {
        if (this.quantityCount > 0) {
            this.quantityCount--;
            this.text_layout_quantity.text = this.quantityCount.toString();
        } else {
            this.btn_decrement.isClickable = false;
        }
    }

    fun isExistQuantity(view: View, value: Int): Int {
        return if (value != 0)
            value
        else
            0
    }

    private fun onDialogConfirm(view: View, priceTotal: Float) {
        val builder: Dialog = Dialog(context!!)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_confirm, null)
        builder.setContentView(dialogLayout)
        builder.setCancelable(false)
        dialogLayout.btn_close_popuup.setOnClickListener { builder.dismiss() }
        dialogLayout.btn_accept_popup.setOnClickListener {
            mWishViewModel.currentUerId.postValue(mUser.uid)
            mWishViewModel.addToCart(mBinding.product, "cliente", quantityCount, additionalNotes, priceTotal)
            builder.dismiss()
            Navigation.findNavController(view).navigate(R.id.action_detailProductFragment_to_homeFragment)
        }
        builder.show()
    }

}
