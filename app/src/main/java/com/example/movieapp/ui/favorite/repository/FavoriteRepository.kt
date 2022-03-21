package com.example.movieapp.ui.favorite.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.data.api.model.MovieDetails
import com.example.movieapp.ui.favorite.room.FavoriteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FavoriteRepository {

    companion object{

        var favoriteDatabase: FavoriteDatabase?=null


        fun initializeDB(context: Context) : FavoriteDatabase {
            return FavoriteDatabase.getInstance(context)
        }

        fun insertInDB(context: Context,result: MovieDetails) {
            favoriteDatabase = initializeDB(context)
            //used coroutineScope because insertMovie() is suspend method and must called from another suspend
            //or coroutine builder
            CoroutineScope(IO).launch {
                favoriteDatabase?.movieDao()?.insertMovie(result)
            }
        }

       suspend fun getMoviesFromDB(context: Context) : LiveData<List<MovieDetails>?>? {
            if (favoriteDatabase == null)  // علشان ميعرفهوش كل مرة
               favoriteDatabase= initializeDB(context)
               return  favoriteDatabase?.movieDao()?.getAllFavMovies()
        }

        fun deleteMoviesFromDB(context: Context,movieId:Int){
            CoroutineScope(IO).launch {
               favoriteDatabase?.movieDao()?.delete(movieId)
                //favoriteList?.clear()  --> مش محتاجة اعمل كلير علشان كدا كدا لو حصل تغيير في الليست هيسمع عندي

            }

        }
      suspend  fun isFavorite(movieId:Int): MovieDetails? {

    return favoriteDatabase?.movieDao()?.isFavorite(movieId)


        }

    }
}