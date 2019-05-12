package com.dezc.coffeesaleapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.activities.DetailProductActivity;
import com.dezc.coffeesaleapp.activities.dummy.DummyContent.DummyItem;
import com.dezc.coffeesaleapp.ui.components.ProductFragment;
import com.dezc.coffeesaleapp.ui.components.ProductFragment.OnListFragmentInteractionListener;

import java.util.Collection;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyProductRecyclerViewAdapter extends RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder> {

    private ProductFragment mContext;
    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyProductRecyclerViewAdapter(ProductFragment context, List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mContext = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    Toast.makeText(
                            mContext.getContext(), "Elemento clickeado: " + holder.mItem, Toast.LENGTH_LONG).show();

                    Intent intentDetailProduct = new Intent(mContext.getContext(), DetailProductActivity.class);
                    intentDetailProduct.putExtra("ID Product", mValues.get(position).id);
                    intentDetailProduct.putExtra("Content Product", mValues.get(position).content);
                    mContext.startActivity(intentDetailProduct);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setItems(Collection<DummyItem> items) {
        mValues.clear();
        mValues.addAll(items);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
