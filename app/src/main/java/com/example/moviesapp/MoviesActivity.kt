package com.example.moviesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.databinding.ActivityMoviesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {
    var binding: ActivityMoviesBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val recyclerView = binding?.recyclerView
        recyclerView?.layoutManager = GridLayoutManager(this, 2)
        val data = ArrayList<ItemsViewModel>()

        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.ic_launcher_foreground, "Item" + i))
        }



        val apiInterface = ApiInterface.create().getMovies()

        apiInterface.enqueue(object: Callback<Movies>, CustomAdapter.ItemClickListener {
            override fun onResponse (call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "OnResponse success ${response?.body()?.results}")

                val adapter = CustomAdapter(response?.body()?.results, this)
                recyclerView?.adapter = adapter
            }

            override fun onFailure (call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "OnFailure ${t?.message}")
            }

            override fun onItemClick(position: Int) {
                val intent = Intent(this@MoviesActivity, MoviesDetailsActivity::class.java)
                intent.putExtra("id", position)
                startActivity(intent)
            Log.d("testLogs", "film`s id: $position")
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}