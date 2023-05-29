package com.example.moviesapp.model.repository

import com.example.moviesapp.data.MovieDetails
import com.example.moviesapp.data.Movies
import retrofit2.Call

interface MoviesDBRepository {
    fun getMovies(): Call<Movies>
    fun getMovieDetails(id: Int, apiKey:String): Call<MovieDetails>
}