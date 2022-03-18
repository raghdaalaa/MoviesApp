package com.example.movieapp.ui.favorite.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.data.api.model.MovieDetails

@Dao
interface FavoriteDao {

    @Query("select * from movies_table ")
     fun getAllFavMovies(): LiveData<List<MovieDetails>?>?

    @Insert
    suspend   fun insertMovie(movie: MovieDetails?)


    //: movieId --> get the value of movieId ==${movieId}
    @Query("delete from movies_table where id= :movieId")
    suspend  fun delete(movieId: Int)

    @Query("select * from movies_table where id=:movieId")
    suspend fun isFavorite(movieId: Int): MovieDetails?
}