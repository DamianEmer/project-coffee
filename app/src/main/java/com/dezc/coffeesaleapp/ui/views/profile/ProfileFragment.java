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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.databinding.FragmentProfileBinding;
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

    private FragmentProfileBinding binding;

    public String profileUri;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textView_name = view.findViewById(R.id.textView_name);
        textView_number_phone = view.findViewById(R.id.textView_number_phone);
        mImageView_profile_photo = (ImageView) view.findViewById(R.id.imageView_profile_photo);

        specific_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Client client = dataSnapshot.getValue(Client.class);
                String email = client.getEmail();
                String name = client.getName();
                String photoUri = client.getProfilePhoto();

                Log.i("ProfileFragment: ", ""+photoUri);
                textView_name.setText(name);
                textView_number_phone.setText(email);
                binding.setProfilePhotoUri(photoUri);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
