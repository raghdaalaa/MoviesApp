package com.example.movieapp.data.api.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_table")
 data class MovieDetails(
   val budget: Int,
   @PrimaryKey(autoGenerate = true)
    val id: Int,
   val overview: String,
   val popularity: Double,
   @SerializedName("poster_path")
    @ColumnInfo val posterPath: String,
   @SerializedName("release_date")
    val releaseDate: String,
   @SerializedName("backdrop_path")
    val backdropPath: String,
   val revenue: Long,
   val runtime: Int,
   val status: String,
   val tagline: String,
   @ColumnInfo val title: String,
   val video: Boolean,
   @SerializedName("vote_average")
    val voteAverage: Double,
   val rating: Double
)
