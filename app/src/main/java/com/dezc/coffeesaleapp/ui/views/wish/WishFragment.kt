package com.dezc.coffeesaleapp.ui.views.wish

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.ui.views.maps.MapsFragment
import com.dezc.coffeesaleapp.fragments.dummy.DummyContent
import com.dezc.coffeesaleapp.models.Product

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class WishFragment : Fragment() {
    // TODO: Customize parameters
    private var mColumnCount = 1
    private val mListener: OnListFragmentInteractionListener? = null

    private var btnSale: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wish_list, container, false)

        btnSale = view.findViewById(R.id.buttonSale)
        btnSale!!.setOnClickListener {
            Log.d("WishFragment: ", "Clicked in the button...")
            val intentMap = Intent(activity, MapsFragment::class.java)
            startActivity(intentMap)
        }

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            //myWishRecyclerViewAdapter = new WishRecyclerViewAdapter(DummyContent.ITEMS);
            view.adapter = myWishRecyclerViewAdapter
        }
        return view
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Product)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"
        val myWishRecyclerViewAdapter = WishRecyclerViewAdapter(DummyContent.ITEMS)

        // TODO: Customize parameter initialization
        fun newInstance(): WishFragment {
            val fragment = WishFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
