package com.dezc.coffeesaleapp.ui.views.profile;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.models.Client;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = db.getReference("clients");

    Query specific_user = myRef.child(user.getUid());

    private TextView textView_name;

    private TextView textView_number_phone;

    private ImageView mImageView_profile_photo;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        textView_name = view.findViewById(R.id.textView_name);
        textView_number_phone = view.findViewById(R.id.textView_number_phone);
        mImageView_profile_photo = (ImageView) view.findViewById(R.id.imageView_profile_photo);

        specific_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue(Client.class).getEmail();
                String name = dataSnapshot.getValue(Client.class).getName();
                String photoUri = "https://firebasestorage.googleapis.com/v0/b/coffee-sale.appspot.com/o/clients%2F72iIOpjIIah2Drw8T40mz8Pxf4B2.jpg?alt=media&token=ced56cec-4bc8-4627-8d3f-c089edef1d5f"; //dataSnapshot.getValue(Client.class).getProfilePhoto();

                Log.i("ProfileFragment: ", ""+photoUri);
                textView_name.setText(name);
                textView_number_phone.setText(email);
                Glide.with(ProfileFragment.this).load(photoUri).into(mImageView_profile_photo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
