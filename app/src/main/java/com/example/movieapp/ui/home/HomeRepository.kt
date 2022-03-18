package com.example.movieapp.ui.home

import com.example.movieapp.data.api.MovieClient
import com.example.movieapp.data.api.MovieInterface
import com.example.movieapp.data.api.model.MovieResponseModel

import com.example.movieapp.data.api.videos.MovieTrailerResponse
import retrofit2.Response


class HomeRepository {
    private val TAG="HomeRepository"
    val API_KEY="2f5c7f8a5b120242529ccef0a81ab720"

    suspend fun getPopular(page:Int): Response<MovieResponseModel> {
        val client=
            MovieClient.getInstance().create(MovieInterface::class.java).getPopularMovies(API_KEY,page)
        client.isSuccessful
        return client
    }


    suspend fun getTopRated(page: Int): Response<MovieResponseModel> {
        val client=
            MovieClient.getInstance().create(MovieInterface::class.java).getTopRatedMovies(API_KEY,page)
        return client
    }
}