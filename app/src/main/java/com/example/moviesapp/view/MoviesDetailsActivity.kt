package com.example.moviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesapp.util.Constants
import com.example.moviesapp.data.MovieDetails
import com.example.moviesapp.databinding.ActivityMoviesDetailsBinding
import com.example.moviesapp.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso

class MoviesDetailsActivity : AppCompatActivity() {

    private var binding: ActivityMoviesDetailsBinding? = null

    private val mViewModel: MoviesViewModel = MoviesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val id = intent.getIntExtra("id", 0)
        initObservers()
        mViewModel.getMovieDetails(id, Constants.API_KEY)

    }

    private fun initObservers () {
        mViewModel.apply {
            movieDetails.observe(this@MoviesDetailsActivity) {
                setMovieInfo(it)
            }
        }
    }

    private fun setMovieInfo (movieDetails: MovieDetails?) {
        binding?.moviesDetailsTitle?.text = movieDetails?.title
        binding?.moviesDetailsDate?.text = movieDetails?.releaseDate
        binding?.moviesDetailsScore?.text = movieDetails?.voteAverage.toString()
        binding?.moviesDetailsBodyOverview?.text = movieDetails?.overview

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+ movieDetails?.backdropPath).into(binding?.moviesDetailsImageBanner)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}