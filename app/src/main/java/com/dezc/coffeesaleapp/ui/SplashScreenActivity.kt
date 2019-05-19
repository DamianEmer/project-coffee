package com.dezc.coffeesaleapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity

import com.dezc.coffeesaleapp.R
import com.dezc.coffeesaleapp.functions.getInteger
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        FirebaseApp.initializeApp(applicationContext)

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity,
                    if (FirebaseAuth.getInstance()?.currentUser == null) HomeActivity::class.java
                    else LoginActivity::class.java))
        }, getInteger(R.integer.splash_screen_time).toLong())
    }
}
