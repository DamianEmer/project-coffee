package com.dezc.coffeesaleapp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.models.Product;
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener;
import com.dezc.coffeesaleapp.viewmodels.ProductViewModel;

public class HomeActivity extends AppCompatActivity implements OnProductClickListener {

    private ProductViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
    }

    @Override
    public void onProductClickListener(Product item) {
        mViewModel.getProduct().postValue(item);
        Navigation.findNavController(this, R.id.nav_host_home).navigate(R.id.action_homeFragment_to_detailProductFragment);
    }
}
