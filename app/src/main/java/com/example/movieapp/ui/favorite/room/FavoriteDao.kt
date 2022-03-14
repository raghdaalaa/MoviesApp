package com.example.movieapp.ui.favorite.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("select * from movies_table ")
    suspend fun getAllFavMovies(): List<FavoriteModel>

    @Insert
    suspend fun insertMovie(movie: FavoriteModel?)

    @Query("delete from movies_table where movieId= :movieId")
    suspend fun delete(movieId: Int)

//    @Query("select * from movies_table where movieId=movieId")
//    suspend fun isFavorite(movieId: String): FavoriteModel
}