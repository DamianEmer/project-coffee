package com.dezc.coffeesaleapp.ui.utils.callbacks;

import com.dezc.coffeesaleapp.models.Product;
import com.dezc.coffeesaleapp.models.ProductCart;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p/>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnProductClickListener {
    void onProductClickListener(Product item);

    void onProductCartClickListener(ProductCart item);

    void onProductCartDeleteClickListener(ProductCart item);
}
