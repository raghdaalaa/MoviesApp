package com.example.movieapp.data.api.model


import com.google.gson.annotations.SerializedName

data class SearchMoviesResponse(
    val page: Int,
    @SerializedName("results")
    val results: ArrayList<MovieDetails>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)


