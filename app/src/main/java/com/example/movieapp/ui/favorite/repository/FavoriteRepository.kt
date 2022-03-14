package com.example.movieapp.ui.favorite.repository

import android.content.Context
import com.example.movieapp.ui.favorite.room.FavoriteModel
import com.example.movieapp.ui.favorite.room.FavoriteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FavoriteRepository {

    companion object{

        var favoriteDatabase: FavoriteDatabase?=null
        var favoriteList: List<FavoriteModel>?=null
        var favoriteModel:FavoriteModel?=null

        fun initializeDB(context: Context) : FavoriteDatabase {
            return favoriteDatabase!!.getInstance(context)
        }

        fun insertInDB(context: Context) {
            favoriteDatabase = initializeDB(context)
            //used coroutineScope because insertMovie() is suspend method and must called from another suspend
            //or coroutine builder
            CoroutineScope(IO).launch {
                favoriteDatabase?.movieDao()?.insertMovie(favoriteModel)
            }
        }

        fun getMoviesFromDB(context: Context) :List<FavoriteModel>?{
            favoriteDatabase= initializeDB(context)
            CoroutineScope(IO).launch {
                 favoriteList= favoriteDatabase?.movieDao()?.getAllFavMovies()
            }
            return favoriteList
        }

        fun deleteMoviesFromDB(context: Context,movieId:Int){
            CoroutineScope(IO).launch {
               favoriteDatabase?.movieDao()?.delete(movieId)

            }

        }
    }
}