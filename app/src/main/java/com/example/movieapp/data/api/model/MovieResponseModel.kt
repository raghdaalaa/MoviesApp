package com.example.moviesapp.data.api.model


import com.google.gson.annotations.SerializedName

data class MovieResponseModel(
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class Result(
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)
{
    fun getRating(): String {
        val rating = (voteAverage * 10).toInt()
        return "$rating%"
    }
}

