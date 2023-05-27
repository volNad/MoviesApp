package com.example.moviesapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/movie/popular")
    fun getMovies(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        page: Int = 1
    ): Call<Movies>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val API_KEY = "17b1b9b339e21803591ae4a249eafcfa"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}