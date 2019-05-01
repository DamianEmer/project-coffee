package com.dezc.coffeesaleapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.activities.MapsActivity;
import com.dezc.coffeesaleapp.adapters.MyWishRecyclerViewAdapter;
import com.dezc.coffeesaleapp.fragments.dummy.DummyContent;
import com.dezc.coffeesaleapp.models.Product;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WishFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    public static final MyWishRecyclerViewAdapter myWishRecyclerViewAdapter = new MyWishRecyclerViewAdapter(DummyContent.ITEMS);

    private Button btnSale;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WishFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WishFragment newInstance() {
        WishFragment fragment = new WishFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        btnSale = view.findViewById(R.id.buttonSale);
        btnSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("WishFragment: ", "Clicked in the button...");
                Intent intentMap = new Intent(getActivity(), MapsActivity.class);
                startActivity(intentMap);
            }
        });

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //myWishRecyclerViewAdapter = new MyWishRecyclerViewAdapter(DummyContent.ITEMS);
            recyclerView.setAdapter(myWishRecyclerViewAdapter);
        }
        return view;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Product item);
    }
}
