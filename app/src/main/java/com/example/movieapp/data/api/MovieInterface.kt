package com.example.movieapp.data.api

import com.example.movieapp.data.api.model.MovieDetails
import com.example.movieapp.data.api.model.MovieResponseModel
import com.example.movieapp.data.api.model.ProductionCountry
import com.example.movieapp.data.api.videos.MovieTrailerResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")api_key:String,
        @Query("page") page: Int
    ):Response<MovieResponseModel>

    @GET("movie/top_rated")
   suspend  fun getTopRatedMovies(
        @Query("api_key")api_key:String,
        @Query("page") page :Int
    ):Response<MovieResponseModel>


    @GET("movie/{movie_id}")
   suspend fun getDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieDetails>

    @GET("movie/{movie_id}")
    suspend fun getMovieCountry(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<ProductionCountry>

    @GET("movie/{movie_id}/videos")
   suspend fun getVideoTrailer(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieTrailerResponse>
}
