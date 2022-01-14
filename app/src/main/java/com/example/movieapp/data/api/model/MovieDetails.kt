package com.example.movieapp.data.api.model


import com.google.gson.annotations.SerializedName

 data class MovieDetails(
    val budget: Int,
    val id: Int,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val rating: Double
)

 {
    fun getRating(): String{
       val rating = (voteAverage*10).toInt()
       return "$rating%"
    }
 }

//data class ProductionCountry(
//   @SerializedName("iso_3166_1")
//   val iso31661: String,
//   val name: String
//)

