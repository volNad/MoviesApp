package com.example.moviesapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.moviesapp.data.User
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.viewmodel.MainActivityViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val mMainActivityViewModel: MainActivityViewModel = MainActivityViewModel()

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { resultCallBack ->
        this.onSignInResult(resultCallBack)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        openRegistrationScreen()
    }

    private fun openRegistrationScreen() {
        val intent = Intent(this@MainActivity, MoviesActivity::class.java)
        startActivity(intent)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                val authUser = FirebaseAuth.getInstance().currentUser
                authUser?.let {
                    val email = it.email.toString()
                    val uid = it.uid
                    val firebaseUser = User(email, uid)

                    mMainActivityViewModel.updateUserData(firebaseUser, uid)

                    val intent = Intent(this@MainActivity, MoviesActivity::class.java)
                    startActivity(intent)
                }


            }
            RESULT_CANCELED -> {
                Toast.makeText(
                    this@MainActivity,
                    "Something wrong with registration",
                    Toast.LENGTH_LONG
                )
                    .show()

                Log.d("testLogs", "RegistrationActivity registration failure")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}