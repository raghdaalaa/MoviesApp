package com.example.moviesapp.data.api

import com.example.moviesapp.data.api.model.MovieDetails
import com.example.moviesapp.data.api.model.MovieResponseModel
import com.example.moviesapp.data.api.model.ProductionCountry
import com.example.moviesapp.data.api.model.Result
import com.example.moviesapp.data.api.videos.MovieTrailerResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

    @GET("movie/popular")
     fun getPopularMovies(
        @Query("api_key")api_key:String,
        @Query("page") page: Int
    ):Response<MovieResponseModel>

    @GET("movie/top_rated")
     fun getTopRatedMovies(
        @Query("api_key")api_key:String,
        @Query("page") page :Int
    ):Response<MovieResponseModel>


    @GET("movie/{movie_id}")
    fun getDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieDetails>

    @GET("movie/{movie_id}")
    fun getMovieCountry(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<ProductionCountry>

    @GET("movie/{movie_id}/videos")
    fun getVideoTrailer(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieTrailerResponse>
}
