package com.dezc.coffeesaleapp.ui.views.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentLoginScreenBinding
import com.dezc.coffeesaleapp.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginScreenFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginScreenBinding

    private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentLoginScreenBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLoginViewModel = ViewModelProviders.of(activity!!).get(LoginViewModel::class.java)
        mLoginViewModel.loginLoading.observe(this, Observer<Boolean> { mBinding.loading = it })
    }

    fun onRegisterNow(view: View) =
            Navigation.findNavController(view).navigate(R.id.action_loginScreenFragment_to_signUpScreenFragment)

    fun onSignIn(view: View) {
        val email = email_input_edit_text.text.toString()
        val password = password_input_edit_text.text.toString()

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(context, "Ingresa tus datos", Toast.LENGTH_LONG).show()
            email_input_edit_text.error = "El campo email no puede ir vacio"
        } else {
            mLoginViewModel.login(email, password) {
                Navigation.findNavController(view).navigate(R.id.action_loginScreenFragment_to_homeActivity)
            }
        }
    }
}
