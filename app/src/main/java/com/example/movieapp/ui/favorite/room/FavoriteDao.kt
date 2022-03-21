package com.example.movieapp.ui.favorite.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.movieapp.data.api.model.MovieDetails
import retrofit2.http.DELETE

@Dao
interface FavoriteDao {

    @Query("select * from movies_table ")
     fun getAllFavMovies(): LiveData<List<MovieDetails>?>?

    @Insert(onConflict = REPLACE)
    suspend   fun insertMovie(movie: MovieDetails?)


    //: movieId --> get the value of movieId ==${movieId}
    @Query("delete from movies_table where id= :movieId")
    suspend  fun delete(movieId: Int)


    @Delete
    suspend  fun deleteMovie(movieDetails: MovieDetails?)

    @Query("select * from movies_table where id=:movieId")
    suspend fun isFavorite(movieId: Int): MovieDetails?
}