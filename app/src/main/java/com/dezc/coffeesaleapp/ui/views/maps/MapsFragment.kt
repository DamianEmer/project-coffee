package com.dezc.coffeesaleapp.ui.views.maps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentMapsBinding

class MapsFragment : Fragment() {

    private lateinit var mBinding: FragmentMapsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentMapsBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {    }

    fun onNext(view: View) {
        Log.d("PaymentFragment: ", "Siguiente sección (Tipo de pago)")
        Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_paymentFragment);
    }

}
