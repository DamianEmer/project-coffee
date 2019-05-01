package com.dezc.coffeesaleapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dezc.coffeesaleapp.R;

public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner dropdownPayment;
    String[] typesPaymentItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        typesPaymentItems = new String[]{"Efectivo", "Tarjeta"};
        dropdownPayment = (Spinner) findViewById(R.id.typePaymentSelect);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.payments_array, android.R.layout.simple_spinner_dropdown_item);

        dropdownPayment.setOnItemSelectedListener(this);
        dropdownPayment.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("PaymentActivity: ", "Selecciono: " + parent.getItemAtPosition(position));
        Toast.makeText(this, "Selecciono " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
