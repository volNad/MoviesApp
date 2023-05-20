package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.databinding.ActivityRegistrationBinding

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}