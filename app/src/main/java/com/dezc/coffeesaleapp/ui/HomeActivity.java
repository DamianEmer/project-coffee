package com.dezc.coffeesaleapp.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.activities.dummy.DummyContent;
import com.dezc.coffeesaleapp.ui.utils.callback.OnListFragmentInteractionListener;

public class HomeActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
    }
}
