package com.dezc.coffeesaleapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dezc.coffeesaleapp.R;

import org.w3c.dom.Text;

public class DetailProductActivity extends AppCompatActivity {

    private TextView nameProduct;
    private TextView priceProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("ID Product") && getIntent().hasExtra("Content Product")){
            String idProduct = getIntent().getStringExtra("ID Product");
            String contentProduct = getIntent().getStringExtra("Content Product");

            setData(idProduct, contentProduct);
        }
    }

    private void setData(String idProduct, String contentProduct){
        nameProduct = (TextView) findViewById(R.id.nameTextView);
        priceProduct = (TextView) findViewById(R.id.priceTextView);

        nameProduct.setText(contentProduct);
        priceProduct.setText(idProduct);
    }

}
