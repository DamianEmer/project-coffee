package com.dezc.coffeesaleapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.fragments.WishFragment;
import com.dezc.coffeesaleapp.fragments.dummy.DummyContent;
import com.dezc.coffeesaleapp.models.Product;

public class DetailProductActivity extends AppCompatActivity {

    private ImageView imageProduct;
    private TextView nameProduct;
    private TextView priceProduct;
    private Button btnAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("ID Product") && getIntent().hasExtra("Content Product")) {
            String idProduct = getIntent().getStringExtra("ID Product");
            String contentProduct = getIntent().getStringExtra("Content Product");

            setData(idProduct, contentProduct);
        }
    }

    private void setData(String idProduct, String contentProduct) {

        imageProduct = (ImageView) findViewById(R.id.imageProduct);
        nameProduct = (TextView) findViewById(R.id.nameTextView);
        priceProduct = (TextView) findViewById(R.id.priceTextView);
        btnAddCart = (Button) findViewById(R.id.button_addCart);

        nameProduct.setText(contentProduct);
        priceProduct.setText(idProduct);
        Glide.with(this).load(R.drawable.coffee).into(imageProduct);

    }

    public void addCart(View view) {
        Toast.makeText(this, "Agregado al carrito", Toast.LENGTH_LONG).show();
        DummyContent.addItem(new Product("001", "Producto nuevo", "x"));
        WishFragment.myWishRecyclerViewAdapter.notifyItemInserted(DummyContent.ITEMS.size() + 1);
    }

}
