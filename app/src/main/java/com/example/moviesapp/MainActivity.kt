package com.example.moviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.moviesapp.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Firebase
            .database("https://moviesapp-7e597-default-rtdb.europe-west1.firebasedatabase.app/")
            .reference

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

// Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)

    }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val authUser = FirebaseAuth.getInstance().currentUser
            authUser?.let {
                val email = it.email.toString()
                val uid = it.uid
                val firebaseUser = User(email, uid)
                database.child("users").child(uid).setValue(firebaseUser)
                val intent = Intent(this@MainActivity, MoviesActivity::class.java)
                startActivity(intent)
            }
            // ...
        } else {
            Toast.makeText(this@MainActivity,
                "Something wrong with registration",
            Toast.LENGTH_LONG)
                .show()

            Log.d("testLogs", "RegistrationActivity registration failure")
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

}