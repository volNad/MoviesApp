package com.example.moviesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.databinding.ActivityMoviesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_foreground, "Item" + i))
        }



        val apiInterface = ApiInterface.create().getMovies()

        apiInterface.enqueue(object: Callback<Movies> {
            override fun onResponse (call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "OnResponse success ${response?.body()?.results}")

                val adapter = CustomAdapter(response?.body()?.results)
                recyclerView.adapter = adapter
            }

            override fun onFailure (call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "OnFailure ${t?.message}")
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}