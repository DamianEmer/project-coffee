package com.dezc.coffeesaleapp.ui.views.maps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentMapsBinding
import com.dezc.coffeesaleapp.functions.addEditTextValidators
import com.dezc.coffeesaleapp.types.Validators
import com.dezc.coffeesaleapp.types.codePostalValidator
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.android.synthetic.main.fragment_maps.view.*

class MapsFragment : Fragment() {

    private var codePostalValidators: Validators = arrayListOf(codePostalValidator())

    private lateinit var mBinding: FragmentMapsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentMapsBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text_input_edit_postal_code.addEditTextValidators(codePostalValidators, "Es requerido el CP")
    }

    fun onNext(view: View) {
        if(text_input_edit_street.text.toString().isNotEmpty() &&
                text_input_edit_suburb.text.toString().isNotEmpty() &&
                text_input_edit_town.text.toString().isNotEmpty() &&
                text_input_edit_city.text.toString().isNotEmpty() &&
                text_input_edit_outdoor_number.text.toString().isNotEmpty() &&
                text_input_edit_postal_code.text.toString().isNotEmpty()){
            Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_paymentFragment)
        }else{
            Toast.makeText(context, "Ingrese sus datos correctamente", Toast.LENGTH_LONG).show()
        }

    }

    fun onBack(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_homeFragment)
    }

}
