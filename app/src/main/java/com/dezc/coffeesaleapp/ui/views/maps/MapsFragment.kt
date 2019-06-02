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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsFragment : Fragment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    private lateinit var mMapReference: MapView

    private lateinit var mBinding: FragmentMapsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentMapsBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mMapReference = map
        mMapReference.onCreate(savedInstanceState)
        mMapReference.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        mMapReference.onStart()
    }

    override fun onResume() {
        super.onResume()
        mMapReference.onResume()
    }

    override fun onStop() {
        super.onStop()
        mMapReference.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapReference.onLowMemory()
    }

    fun onNext(view: View) {
        Log.d("PaymentFragment: ", "Siguiente sección (Tipo de pago)")
        Navigation.findNavController(view).navigate(R.id.action_mapsFragment_to_paymentFragment);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
