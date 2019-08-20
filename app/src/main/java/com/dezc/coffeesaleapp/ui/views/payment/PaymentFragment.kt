package com.dezc.coffeesaleapp.ui.views.payment

import android.R.layout.simple_spinner_dropdown_item
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import com.dezc.coffeesaleapp.BR
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentPaymentBinding
import com.dezc.coffeesaleapp.models.Address
import com.dezc.coffeesaleapp.models.Client
import com.dezc.coffeesaleapp.models.Order
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.viewmodels.OrderFlowViewModel
import com.dezc.coffeesaleapp.viewmodels.WishViewModel
import com.google.android.gms.common.internal.GmsClientEventManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import io.reactivex.internal.util.NotificationLite
import kotlinx.android.synthetic.main.dialog_additional.*
import kotlinx.android.synthetic.main.dialog_additional.view.*
import kotlinx.android.synthetic.main.dialog_confirm.view.*
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlinx.android.synthetic.main.fragment_product_list.*

class PaymentFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var mBinding: FragmentPaymentBinding

    private lateinit var mWishViewModel: WishViewModel

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        mBinding = FragmentPaymentBinding.inflate(inflater)
        mBinding.context = this
        mBinding.effectiveQuantity!!.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
               
            }

        })
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        type_payment_select.onItemSelectedListener = this
        mWishViewModel = ViewModelProviders.of(activity!!).get(WishViewModel::class.java)
        type_payment_select.adapter = ArrayAdapter.createFromResource(context!!, R.array.payment_array, simple_spinner_dropdown_item)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent!!.getItemAtPosition(position).toString() == "Efectivo") {
            mBinding.showing = true
            mWishViewModel.typePayment.postValue(parent.getItemAtPosition(position).toString())
        } else {
            mBinding.showing = false
            mWishViewModel.typePayment.postValue(parent.getItemAtPosition(position).toString())
        }
    }

    fun onOrder(view: View){
        Log.i("PaymentFragment", "Orden activa")
        mWishViewModel.effectiveQuantity.postValue(text_quantity_effective.text.toString()
                .let { if(it.isNotEmpty()) it.toFloat() else "0.00".toFloat() })
        this.onDialogConfirm(view)
    }

    fun onCancel(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_paymentFragment_to_homeFragment)
    }

    fun onAdditionalIngredient(view: View) {
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_additional, null)
        val builder = AlertDialog.Builder(context!!)
        builder.setView(dialogLayout)
        builder.setTitle("Ingrediente adicional")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { _, _ ->
            mWishViewModel.additionalNotes.postValue(dialogLayout.text_ingredient.text.toString()
                    .let { if(it.isNotEmpty()) it else "none" })
        }.setNegativeButton("Cancel") { _, _ ->
        }
        builder.show()
    }


    fun onDialogConfirm(view: View){
        val builder: Dialog = Dialog(context!!)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_confirm, null)
        builder.setContentView(dialogLayout)
        builder.setCancelable(false)
        dialogLayout.btn_close_popuup.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                builder.dismiss()
            }

        })
        dialogLayout.btn_accept_popup.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                mWishViewModel.onOrder()
                builder.dismiss()
                Navigation.findNavController(view).navigate(R.id.action_paymentFragment_to_homeFragment)
            }
        })
        builder.show()
    }
}
