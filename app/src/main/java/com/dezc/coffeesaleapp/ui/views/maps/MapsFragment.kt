package com.dezc.coffeesaleapp.ui.views.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentMapsBinding
import com.dezc.coffeesaleapp.functions.addEditTextValidators
import com.dezc.coffeesaleapp.models.Address
import com.dezc.coffeesaleapp.types.Validators
import com.dezc.coffeesaleapp.types.codePostalValidator
import com.dezc.coffeesaleapp.viewmodels.OrderFlowViewModel
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsFragment : Fragment() {

    private var codePostalValidators: Validators = arrayListOf(codePostalValidator())

    private lateinit var mBinding: FragmentMapsBinding

    private lateinit var mOrderFlowViewModel: OrderFlowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentMapsBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mOrderFlowViewModel = ViewModelProviders.of(activity!!).get(OrderFlowViewModel::class.java)
        text_input_edit_postal_code.addEditTextValidators(codePostalValidators, "Es requerido el CP")
    }

    fun onNext(view: View) {
        if (text_input_edit_street.text.toString().isNotEmpty() &&
                text_input_edit_suburb.text.toString().isNotEmpty() &&
                text_input_edit_town.text.toString().isNotEmpty() &&
                text_input_edit_city.text.toString().isNotEmpty() &&
                text_input_edit_outdoor_number.text.toString().isNotEmpty() &&
                text_input_edit_postal_code.text.toString().isNotEmpty()) {
            val address: Address = Address(text_input_edit_street.text.toString(),
                    Integer.parseInt(text_input_edit_postal_code.text.toString()),
                    Integer.parseInt(text_input_edit_outdoor_number.text.toString()),
                    text_input_edit_interior_number.text.toString().let { if (it.isNotEmpty()) Integer.parseInt(it) else 0 },
                    text_input_edit_suburb.text.toString(),
                    text_input_edit_city.text.toString(),
                    text_input_edit_town.text.toString())
            mOrderFlowViewModel.address.postValue(address)
            Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_paymentFragment)
        } else {
            Toast.makeText(context, "Ingrese sus datos correctamente", Toast.LENGTH_LONG).show()
        }

    }

    fun onBack(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_homeFragment)
    }

}
