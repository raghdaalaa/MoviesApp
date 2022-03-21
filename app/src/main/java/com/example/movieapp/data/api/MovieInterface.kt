package com.example.movieapp.data.api

import com.example.movieapp.data.api.model.*
import com.example.movieapp.data.api.videos.MovieTrailerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {

    @GET("movie/popular?")
    suspend fun getPopularMovies(
        @Query("api_key")api_key:String,
        @Query("page") page: Int
    ):Response<MovieResponseModel>

    @GET("movie/top_rated?")
   suspend  fun getTopRatedMovies(
        @Query("api_key")api_key:String,
        @Query("page") page :Int
    ):Response<MovieResponseModel>


    @GET("movie/{movie_id}?")
   suspend fun getDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieDetails>


    @GET("movie/{movie_id}/videos?")
   suspend fun getVideoTrailer(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): Response<MovieTrailerResponse>

   @GET("search/movie?")
   suspend fun searchMovie(
       @Query("api_key") api_key: String,
       @Query("query") query:String
   ):Response<SearchMoviesResponse>

   @GET("person/{person_id}")
   suspend fun actor(
       @Path("person_id") person_id: Int,
       @Query("api_key") api_key: String,
   ):Response<CastResult>
}
