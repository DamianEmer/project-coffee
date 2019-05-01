package com.dezc.coffeesaleapp.interfaces;

import android.content.Context;
import android.net.Uri;

import com.dezc.coffeesaleapp.models.Client;

public interface ClientDAO {

    void onSaveClient(Client client, Context context, Uri imageUri);

}
