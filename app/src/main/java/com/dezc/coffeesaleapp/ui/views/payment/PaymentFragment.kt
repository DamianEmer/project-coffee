package com.dezc.coffeesaleapp.ui.views.payment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentPaymentBinding
import kotlinx.android.synthetic.main.fragment_payment.*
import android.R.layout.simple_spinner_dropdown_item

class PaymentFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var mTypesPaymentItems: Array<String>? = null

    private lateinit var mBinding: FragmentPaymentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentPaymentBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mTypesPaymentItems = arrayOf("Efectivo", "Tarjeta")
        type_payment_select.onItemSelectedListener = this
        type_payment_select.adapter = ArrayAdapter.createFromResource(context!!, R.array.payments_array, simple_spinner_dropdown_item)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        Log.d("PaymentFragment: ", "Selecciono: " + parent.getItemAtPosition(position))
        Toast.makeText(context, "Selecciono " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
}
