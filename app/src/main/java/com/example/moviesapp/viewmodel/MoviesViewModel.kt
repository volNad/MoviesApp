package com.example.moviesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.MovieDetails
import com.example.moviesapp.data.Movies
import com.example.moviesapp.model.repository.MoviesDBRepository
import com.example.moviesapp.model.repository.MoviesDBRepositoryImpl
import com.example.moviesapp.data.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel: ViewModel(){

    private val _movies = MutableLiveData<List<Result?>>()
    val movies: LiveData<List<Result?>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails?>()
    val movieDetails: LiveData<MovieDetails?> = _movieDetails

    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                Log.d("testLogs", "OnResponse Success ${call.toString()} ")
                _movies.postValue(response?.body()?.results)
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("testLogs", "OnFailure : ${t.message}")
            }

        })
    }

    fun getMovieDetails(id: Int, apiKey: String) {
        val response = mMoviesRepository.getMovieDetails(id, apiKey)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {
                Log.d("testLogs", "OnResponse Success ${call.toString()} ")
                _movieDetails.postValue(response?.body())
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("testLogs", "OnFailure : ${t.message}")
            }

        })
    }
}