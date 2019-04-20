package com.dezc.coffeesaleapp.interfaces;

import android.content.Context;

import com.dezc.coffeesaleapp.models.Client;

public interface ClientDAO {

    void onSaveClient(Client client, Context context);

}
