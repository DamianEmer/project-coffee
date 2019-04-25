package com.dezc.coffeesaleapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.activities.SignUpActivity;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements TextWatcher {

    private TextView email;
    private TextView password;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EmailChanged mListener;
    private PasswordChanged pListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        email = (TextView) view.findViewById(R.id.emailInputEditText);
        password = (TextView) view.findViewById(R.id.passwordInputEditText);
        email.addTextChangedListener(this);
        password.addTextChangedListener(this);
        SignUpActivity signUpAct = (SignUpActivity) getActivity();
        signUpAct.emailAndPassword(email.getText().toString(), password.getText().toString());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EmailChanged && context instanceof PasswordChanged) {
            mListener = (EmailChanged) context;
            pListener = (PasswordChanged) context;
        }else
         {
            throw new RuntimeException(context.toString()
                    + " must implement EmailChanged");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mListener != null && pListener != null) {
            if(s == email.getEditableText()){
                mListener.onEmailChanged(s.toString());
            }else if(s == password.getEditableText()){
                pListener.onPasswordChanged(s.toString());
            }
        }

    }



    public interface EmailChanged {
        // TODO: Update argument type and name
        void onEmailChanged(String newText);
    }

    public interface PasswordChanged {
        void onPasswordChanged(String passwordTxt);
    }
}
