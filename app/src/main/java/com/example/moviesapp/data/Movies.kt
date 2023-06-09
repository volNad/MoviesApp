package com.example.moviesapp.data


import com.google.gson.annotations.SerializedName

data class Movies(
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)