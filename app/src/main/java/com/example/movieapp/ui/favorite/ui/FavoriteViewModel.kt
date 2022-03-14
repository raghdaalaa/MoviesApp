package com.example.movieapp.ui.favorite.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.viewmodels.DetailsViewModel
import com.example.movieapp.ui.favorite.room.FavoriteModel
import com.example.movieapp.ui.favorite.repository.FavoriteRepository
import com.example.movieapp.ui.favorite.room.FavoriteDao
import com.example.movieapp.ui.favorite.room.FavoriteDatabase
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {

    private val TAG = "FavoriteViewModel"
    var movies=MutableLiveData<List<FavoriteModel?>>()


fun insertMovie(context: Context){

    FavoriteRepository.insertInDB(context)
}

fun deleteMovie(context: Context, movieId:Int){

    FavoriteRepository.deleteMoviesFromDB(context,movieId)
}

fun getMovies(context: Context) :List<FavoriteModel>?{
    movies=FavoriteRepository.getMoviesFromDB(context)
    return movies
}
}