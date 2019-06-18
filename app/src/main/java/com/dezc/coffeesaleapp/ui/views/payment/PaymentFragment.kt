package com.dezc.coffeesaleapp.ui.views.payment

import android.R.layout.simple_spinner_dropdown_item
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentPaymentBinding
import com.dezc.coffeesaleapp.models.Address
import com.dezc.coffeesaleapp.models.Client
import com.dezc.coffeesaleapp.models.Order
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.viewmodels.OrderFlowViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.dialog_additional.*
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var mBinding: FragmentPaymentBinding

    private lateinit var mOrderFlowViewModel: OrderFlowViewModel

    private lateinit var mProducts: List<Product>

    private lateinit var mAddress: Address

    private lateinit var mPaymentType: String

    private val mUser: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    private val mDatabaseReference = FirebaseDatabase.getInstance()
            .getReference("clients")

    private var mShowField: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentPaymentBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        type_payment_select.onItemSelectedListener = this
        mOrderFlowViewModel = ViewModelProviders.of(activity!!).get(OrderFlowViewModel::class.java)
        type_payment_select.adapter = ArrayAdapter.createFromResource(context!!, R.array.payment_array, simple_spinner_dropdown_item)

        // Captura de informacion de algun cambio
        mOrderFlowViewModel.allProducts.observe(this, Observer { products ->
            this.mProducts = products
        })

        mOrderFlowViewModel.address.observe(this, Observer { address ->
            this.mAddress = address
        })

        mDatabaseReference.child(mUser.getUid())
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        //Log.i("PaymentFragment", "Producto: ${mProducts.get(1).name} - Type: ${mPaymentType}")
                        mOrderFlowViewModel.client.postValue(snapshot.getValue(Client::class.java))
                    }

                    override fun onCancelled(p0: DatabaseError) {

                    }
                })
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (parent.getItemAtPosition(position).toString() == "Efectivo") {
            mBinding.showing = true
            mOrderFlowViewModel.paymentType.postValue(parent.getItemAtPosition(position).toString())
            mOrderFlowViewModel.paymentType.observe(this, Observer { paymentType ->
                this.mPaymentType = paymentType
            })
            Toast.makeText(context, "Selecciono " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show()
        } else {
            mBinding.showing = false
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }

    // Metodoque cargara la informacion hacia la base de datos

    fun onOrder(view: View) {
        mOrderFlowViewModel.client.observe(this, Observer { client ->
            Log.i(">>> PaymentFragment: ", "Cantidad efectivo: ${client.name}")
        })
        Navigation.findNavController(view).navigate(R.id.action_paymentFragment_to_statusOrderFragment2)
    }

    fun onCancel(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_paymentFragment_to_homeFragment)
    }

    fun onAdditionalIngredient(view: View) {
        val builder = AlertDialog.Builder(context!!)
        val inflater = layoutInflater
        builder.setTitle("Ingrediente adicional")
        val dialogLayout = inflater.inflate(R.layout.dialog_additional, null)
        builder.setView(dialogLayout)
        builder.setPositiveButton("OK") { p0, p1 ->

            //Componer seccion porque no es effectiveQuantity
            if (text_ingredient.text.toString().isNotEmpty()) {
                Log.i("PaymentFragment", "No vacio")
                mOrderFlowViewModel.effectiveQuantity.postValue(Integer.parseInt(text_ingredient.text.toString()))
            } else {
                Toast.makeText(context, "Ingrese ingrediente para avanzar..", Toast.LENGTH_LONG).show()
            }
        }.setNegativeButton("Cancel") { p0, p1 ->
            Log.i("PaymentFragment", "Ningun ingrediente")
        }
        builder.show()
    }
}
