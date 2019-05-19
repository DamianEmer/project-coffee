package com.dezc.coffeesaleapp.ui.views.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.models.Product
import com.dezc.coffeesaleapp.ui.utils.callback.OnProductClickListener
import com.google.firebase.database.*

import java.util.ArrayList

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
class ProductFragment : Fragment() {

    private val mProductsDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance()
            .reference.child("products")

    val productList: MutableList<Product> = ArrayList()

    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: OnProductClickListener? = null

    private var mAdapter: ProductRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        mProductsDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {

                    productList.clear()

                    for (ds in dataSnapshot.getChildren()) {
                        val id: Int = Integer.parseInt(ds.key)
                        val name: String = ds.child("name").getValue().toString()
                        val price: String = ds.child("price").getValue().toString()
                        val description: String = ds.child("description").getValue().toString()

                        productList.add(Product(id, name, price, description))

                        // Set the adapter
                        if (view is RecyclerView) {
                            with(view) {
                                layoutManager = when {
                                    mColumnCount <= 1 -> LinearLayoutManager(context)
                                    else -> GridLayoutManager(context, mColumnCount)
                                }
                                mAdapter = ProductRecyclerViewAdapter(
                                        this@ProductFragment,
                                        productList,
                                        mListener)

                                view.adapter = mAdapter
                            }
                        }
                    }
                }

            }
        })
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnProductClickListener) {
            mListener = context
        } else {
            throw RuntimeException("$context must implement OnProductClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    companion object {

        //private ProductListener productListener;
        //private Button productBtn;

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(): ProductFragment {
            val fragment = ProductFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
