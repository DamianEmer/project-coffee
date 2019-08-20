package com.dezc.coffeesaleapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.models.Product;
import com.dezc.coffeesaleapp.models.ProductCart;
import com.dezc.coffeesaleapp.ui.utils.callbacks.OnProductClickListener;
import com.dezc.coffeesaleapp.viewmodels.ProductViewModel;
import com.dezc.coffeesaleapp.viewmodels.WishViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements OnProductClickListener {

    private ProductViewModel mProductViewModel;

    private WishViewModel mWishViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mWishViewModel = ViewModelProviders.of(this).get(WishViewModel.class);
    }

    @Override
    public void onProductClickListener(Product item) {
        Log.i(HomeActivity.class.getName(), "product click "+item.getName());
        mProductViewModel.getProduct().postValue(item);
        Navigation.findNavController(this, R.id.nav_host_home).navigate(R.id.action_homeFragment_to_detailProductFragment);
    }

    @Override
    public void onProductCartClickListener(ProductCart item){
        Log.i(HomeActivity.class.getName(), "productCart click "+item.getIdProduct());
        mProductViewModel.getProductSelect().postValue(item);
        Navigation.findNavController(this, R.id.nav_host_home).navigate(R.id.action_homeFragment_to_detailProductFragment);
    }

    @Override
    public void onProductCartDeleteClickListener(ProductCart item) {
        Log.i("HomeActivity", "DELETE......");
        mWishViewModel.onDeleteProductCart(item.getIdCart());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.loginActivity == item.getItemId()) {
            FirebaseAuth.getInstance().signOut();
            NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_home));
        }else{
            if (R.id.purchase_status == item.getItemId()){
                Log.i(">>>> HomeActivity: ", "Ver status");
                Navigation.findNavController(this, R.id.nav_host_home).navigate(R.id.action_homeFragment_to_statusOrderFragment);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
