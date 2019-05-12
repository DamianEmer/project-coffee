package com.dezc.coffeesaleapp.ui.components;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.dezc.coffeesaleapp.R;
import com.dezc.coffeesaleapp.ui.utils.callback.EmailChanged;
import com.dezc.coffeesaleapp.ui.utils.callback.PasswordChanged;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment implements TextWatcher {

    private TextView email;
    private TextView password;

    private EmailChanged mListener;
    private PasswordChanged pListener;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        email = (TextView) view.findViewById(R.id.email_input_edit_text);
        password = (TextView) view.findViewById(R.id.password_input_edit_text);
        email.addTextChangedListener(this);
        password.addTextChangedListener(this);
        return view;
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        Log.i("LoginFragment", context.toString());
        if (context instanceof EmailChanged && context instanceof PasswordChanged) {
            mListener = (EmailChanged) context;
            pListener = (PasswordChanged) context;
        } else {
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
            if (s == email.getEditableText()) {
                mListener.onEmailChanged(s.toString());
            } else if (s == password.getEditableText()) {
                pListener.onPasswordChanged(s.toString());
            }
        }
    }
}
