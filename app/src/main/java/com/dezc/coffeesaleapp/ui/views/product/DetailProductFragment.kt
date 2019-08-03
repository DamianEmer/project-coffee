package com.dezc.coffeesaleapp.ui.views.product

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
import com.dezc.coffeesaleapp.functions.addEditTextValidators
import com.dezc.coffeesaleapp.functions.isNotEmpty
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.types.Validators
import com.dezc.coffeesaleapp.viewmodels.ProductViewModel
import com.dezc.coffeesaleapp.viewmodels.WishViewModel
import com.dezc.coffeesaleapp.types.quantityValidator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.dialog_additional.*
import kotlinx.android.synthetic.main.dialog_additional.view.*
import kotlinx.android.synthetic.main.fragment_detail_product.*

class DetailProductFragment : Fragment() {

    private lateinit var mProductViewModel: ProductViewModel

    private lateinit var mWishViewModel: WishViewModel

    private lateinit var mBinding: FragmentDetailProductBinding

    private var quantityValidators: Validators = arrayListOf(quantityValidator(1))

    private val mUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentDetailProductBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mProductViewModel = ViewModelProviders.of(activity!!).get(ProductViewModel::class.java)
        mWishViewModel = ViewModelProviders.of(activity!!).get(WishViewModel::class.java)
//        text_quantity.addEditTextValidators(quantityValidators, "Se requiere una cantidad")
        mProductViewModel.product.observe(this, Observer { setData(it) })
    }

    private fun setData(product: Product) {
        mBinding.product = product
        Glide.with(this).load(R.drawable.coffee).override(800,200).into(image_product)
    }

    fun addCart(view: View) {
        if(this.quantityCount >= 1) {
            val priceTotal: Float = java.lang.Float.parseFloat((mBinding.product!!.price * this.quantityCount).toString());
            mWishViewModel.currentUerId.postValue(mUser.uid)
            mWishViewModel.addToCart(mBinding.product, "cliente", this.quantityCount, this.additionalNotes, priceTotal);
        }else {
            Toast.makeText(view.context, "Ingresa una cantidad", Toast.LENGTH_LONG).show()
        }
    }

    var additionalNotes: String = "";

    fun onCreateModal(view: View){
        val inflater = layoutInflater;
        val dialogLayout = inflater.inflate(R.layout.dialog_additional, null);
        val builder = AlertDialog.Builder(context!!);
        builder.setTitle("Notas adicionales");
        builder.setView(dialogLayout);
        builder.setCancelable(true);
        builder.setPositiveButton("OK") { dialog, which ->
            this.additionalNotes = dialogLayout.text_ingredient.text.toString()
        }.setNegativeButton("Cancel") { dialog, which ->
        }
        builder.show();
    }

    var quantityCount: Int = 0;

    fun onIncrementeCounter(view: View){
        this.quantityCount++;
        this.text_layout_quantity.text = this.quantityCount.toString();
        if(this.quantityCount > 0) this.btn_decrement.isClickable = true;
    }

    fun onDecrementCouter(view: View) {
        if(this.quantityCount > 0) {
            this.quantityCount--;
            this.text_layout_quantity.text = this.quantityCount.toString();
        }else {
            this.btn_decrement.isClickable = false;
        }

    }
}
