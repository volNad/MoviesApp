package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_foreground, "Item" + i))
        }

        val adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}