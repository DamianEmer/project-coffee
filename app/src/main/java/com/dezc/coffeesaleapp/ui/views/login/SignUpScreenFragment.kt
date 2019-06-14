package com.dezc.coffeesaleapp.ui.views.login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentSignUpScreenBinding
import com.dezc.coffeesaleapp.functions.isNotEmpty
import com.dezc.coffeesaleapp.models.Client
import com.dezc.coffeesaleapp.types.Validators
import com.dezc.coffeesaleapp.types.emailValidator
import com.dezc.coffeesaleapp.types.passwordValidator
import com.dezc.coffeesaleapp.types.whatsAppNumberValidator
import com.dezc.coffeesaleapp.functions.addEditTextValidators
import com.dezc.coffeesaleapp.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_sign_up_screen.*

class SignUpScreenFragment : Fragment() {

    private lateinit var mBinding: FragmentSignUpScreenBinding

    private val PICK_IMAGE_REQUEST = 1

    private lateinit var imageUri: Uri

    private var emailValidators: Validators = arrayListOf(emailValidator)

    private var whatsAppValidators: Validators = arrayListOf(whatsAppNumberValidator)

    private var passwordValidators: Validators = arrayListOf(passwordValidator(8))

    private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentSignUpScreenBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLoginViewModel = ViewModelProviders.of(activity!!).get(LoginViewModel::class.java)
        mLoginViewModel.loginLoading.observe(this, Observer<Boolean> { mBinding.loading = it })
        mLoginViewModel.signUpProgress.observe(this, Observer<Int> { mBinding.progress = it })
        whatsAppNumberInputEditText.addEditTextValidators(whatsAppValidators, getString(R.string.required_field, "número"))
        password_input_edit_text.addEditTextValidators(passwordValidators, getString(R.string.required_field, "password"))
        email_input_edit_text.addEditTextValidators(emailValidators, getString(R.string.required_field, "email"))
    }

    fun onSaveClient(view: View) {
        if (nameInputEditText.text.toString().isNotEmpty()
                && lastnameInputEditText.text.toString().isNotEmpty()
                && secondLastnameInputEditText.text.toString().isNotEmpty()
                && whatsAppNumberInputEditText.text.toString().isNotEmpty()
                && email_input_edit_text.isNotEmpty()
                && password_input_edit_text.isNotEmpty()
                && ::imageUri.isLateinit) {
            mLoginViewModel.signUp(Client(
                    nameInputEditText.text.toString(),
                    lastnameInputEditText.text.toString(),
                    secondLastnameInputEditText.text.toString(),
                    whatsAppNumberInputEditText.text.toString(),
                    email_input_edit_text.text.toString(),
                    password_input_edit_text.text.toString()),
                    imageUri,
                    onSignUp(view))
        } else {
            Toast.makeText(context, "Faltan algunos datos", Toast.LENGTH_SHORT).show();
        }
    }

    private fun onSignUp(view: View): (Void) -> Unit {
        return {
            Navigation.findNavController(view).navigate(R.id.action_loginScreenFragment_to_homeActivity)
        }
    }

    fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.data != null) {
            imageUri = data.data!!
            Glide.with(this).load(imageUri).into(profile_photo_image_view)

        }
    }

    fun onExistingAccount(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_signUpScreenFragment_to_loginScreenFragment)
    }
}
