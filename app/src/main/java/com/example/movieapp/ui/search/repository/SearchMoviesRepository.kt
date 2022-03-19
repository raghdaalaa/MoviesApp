package com.example.movieapp.ui.search.repository

import com.example.movieapp.data.api.MovieClient
import com.example.movieapp.data.api.MovieInterface
import com.example.movieapp.data.api.model.SearchMoviesResponse
import retrofit2.Response

class SearchMoviesRepository {

    val API_KEY="2f5c7f8a5b120242529ccef0a81ab720"

    suspend fun getSearch(query:String) : Response<SearchMoviesResponse> {

        val client=MovieClient.getInstance().create(MovieInterface ::class.java).searchMovie(API_KEY,query)
        client.isSuccessful
        return client

    }

}