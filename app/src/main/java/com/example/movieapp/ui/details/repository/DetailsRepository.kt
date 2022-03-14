package com.example.movieapp.ui.details.repository

import com.example.movieapp.data.api.MovieClient
import com.example.movieapp.data.api.MovieInterface
import com.example.movieapp.data.api.model.MovieDetails

class DetailsRepository {
    private val TAG = "DetailsRepository"
    val API_KEY = "2f5c7f8a5b120242529ccef0a81ab720"


    suspend fun getMoviesDetails(id: Int): MovieDetails? {
        val client =
            MovieClient.getInstance().create(MovieInterface::class.java).getDetails(id, API_KEY)
        client.isSuccessful
        return client.body()
    }

    suspend fun getTrailer(id: Int) = MovieClient.getInstance().create(MovieInterface::class.java)
        .getVideoTrailer(id, API_KEY)


//    suspend fun getFav(id: Int): Boolean {
////get from db
//    }
//
//    suspend fun setFav(id: Int,poster:String,title:String,exist:Boolean) {
//
//    }
}
