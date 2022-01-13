package com.example.moviesapp.ui.details.repository

import com.example.moviesapp.data.api.MovieClient
import com.example.moviesapp.data.api.MovieInterface
import com.example.moviesapp.data.api.model.MovieDetails
import com.example.moviesapp.data.api.model.ProductionCountry
import com.example.moviesapp.data.api.videos.ResultTrailerResponse

class DetailsRepository {
    private val TAG="DetailsRepository"
    val API_KEY="2f5c7f8a5b120242529ccef0a81ab720"

    suspend fun getMoviesDetails(id:Int):MovieDetails?{
        val client=
            MovieClient.getInstance().create(MovieInterface::class.java).getDetails(id,API_KEY)
        client.isSuccessful
        return client.body()
    }

    suspend fun getTrailer(id: Int):ResultTrailerResponse?{
        val client=MovieClient.getInstance().create(MovieInterface::class.java).getVideoTrailer(id,API_KEY)
        var resultsVideoItem:ResultTrailerResponse?=null
        if (client.body()?.resultTrailerResponses != null){
            for (i in client.body()?.resultTrailerResponses!!){
                if (i?.type=="Trailer"){
                     resultsVideoItem=i
                    break
                }else {
                    resultsVideoItem=i
                }
            }
        }
        return resultsVideoItem
    }

    suspend fun getMoviesCountry(id:Int):ProductionCountry?{
        val client=
            MovieClient.getInstance().create(MovieInterface::class.java).getMovieCountry(id,API_KEY)
        client.isSuccessful
        return client.body()
    }
}