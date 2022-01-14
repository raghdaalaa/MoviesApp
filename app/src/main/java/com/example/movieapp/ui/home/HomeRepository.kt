package com.example.movieapp.ui.home

import com.example.movieapp.data.api.MovieClient
import com.example.movieapp.data.api.MovieInterface
import com.example.movieapp.data.api.model.Result


class HomeRepository {
    val API_KEY="2f5c7f8a5b120242529ccef0a81ab720"
    private val TAG="HomeRepository"

    suspend fun getPopular():List<Result>?{
        val client=
            MovieClient.getInstance().create(MovieInterface::class.java).getPopularMovies(API_KEY,1)
        return client.body()?.results
    }


    suspend fun getTopRated():List<Result>?{
        val client=
            MovieClient.getInstance().create(MovieInterface::class.java).getTopRatedMovies(API_KEY,1)
        return client.body()?.results
    }


}