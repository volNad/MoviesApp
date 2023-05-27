package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.databinding.ActivityMoviesDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDetailsActivity : AppCompatActivity() {

     private var binding: ActivityMoviesDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val id = intent.getIntExtra("id", 0)
        Log.d("testing", "id is $id")

        val apiInterface = id.let {
            ApiInterface.create().getMovieDetails(it, Constants.API_KEY)
        }

        apiInterface.enqueue(object: Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                binding?.moviesDetailsTitle?.text = response.body()?.title
                binding?.moviesDetailsDate?.text = response.body()?.releaseDate.toString()
                binding?.moviesDetailsScore?.text = response.body()?.voteAverage?.toString()
                binding?.moviesDetailsBodyOverview?.text = response.body()?.overview

                Picasso.get().load("https://image.tmdb.org/t/p/w500" + response.body()?.backdropPath.toString()).into(
                    binding?.moviesDetailsImageBanner)
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("testLogs", "onFailure: ${t.message}")
            }

        })

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}