package com.dezc.coffeesaleapp.ui.views.login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.databinding.FragmentSignUpScreenBinding
import com.dezc.coffeesaleapp.models.Validator
import com.dezc.coffeesaleapp.models.emailValidator
import com.dezc.coffeesaleapp.models.whatsAppNumberValidator
import com.dezc.coffeesaleapp.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_sign_up_screen.*

class SignUpScreenFragment : Fragment() {

    private lateinit var mBinding: FragmentSignUpScreenBinding

    private val PICK_IMAGE_REQUEST = 1

    private var imageUri: Uri? = null

    private var email: String = ""

    private var password: String = ""

    private var emailValidators: List<Validator> = arrayListOf(emailValidator)

    private var whatsAppValidators: List<Validator> = arrayListOf(whatsAppNumberValidator)

    private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentSignUpScreenBinding.inflate(inflater)
        mBinding.context = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLoginViewModel = ViewModelProviders.of(activity!!).get(LoginViewModel::class.java)
        mLoginViewModel.loginLoading.observe(this, Observer<Boolean> { mBinding.loading = it })
        mLoginViewModel.progress.observe(this, Observer<Int> { mBinding.progress = it })
    }

    /*
        private var name: TextView? = null
        private var lastname: TextView? = null
        private var secondaLastname: TextView? = null
        private var numberWhatsapp: TextView? = null

        private var chooseImageButton: Button? = null
        private var profilePhoto: ImageView? = null
        private var progressBar: ProgressBar? = null

        private val clientDaoImpl = ClientDAOImpl()
    */

/*
    private fun validateInputPassword(password: String?): Boolean {
        if (password != null) {
            if (password.length >= 8) {
                return true
            } else {
                Toast.makeText(this, "La contraseña debe ser mayor a 8 caracteres", Toast.LENGTH_LONG).show()
                return false
            }
        } else {
            Toast.makeText(this, "Ingrese una password", Toast.LENGTH_SHORT).show()
            return false
        }
    }

   */

    fun onSaveClient(view: View) {
        /*val date = Date()
        // name = findViewById<TextView>(R.id.nameInputEditText)
        // lastname = findViewById<TextView>(R.id.lastnameInputEditText)
        // secondaLastname = findViewById<TextView>(R.id.secondLastnameInputEditText)
        // numberWhatsapp = findViewById<TextView>(R.id.whatsappNumberInputEditText)
        val client = Client(
                name!!.text.toString(),
                lastname!!.text.toString(),
                secondaLastname!!.text.toString(),
                numberWhatsapp!!.text.toString(),
                email,
                password,
                if (imageUri != null) imageUri!!.toString() else ""
        )

        if (validateInputEmail(email) && validateInputPassword(password)
                && validateInputNumberWhatsapp(numberWhatsapp!!.text.toString())) {
            //   clientDaoImpl.onSaveClient(client, getApplicationContext(), imageUri)
        }*/

    }

    fun openFileChooser(view: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.data != null) {
            imageUri = data.data

            Glide.with(this).load(imageUri).into(profile_photo_image_view)
        }
    }

    fun onExistingAccount(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_signUpScreenFragment_to_loginScreenFragment)
    }
}
