package com.example.moviesapp.model.repository

import com.example.moviesapp.util.Constants
import com.example.moviesapp.data.MovieDetails
import com.example.moviesapp.data.Movies
import com.example.moviesapp.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.API_KEY)
    }

    override fun getMovieDetails(id: Int, apiKey: String): Call<MovieDetails> {
        return apiInterface.getMovieDetails(id, apiKey)
    }
}