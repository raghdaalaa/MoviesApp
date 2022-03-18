package com.example.movieapp.ui.favorite.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.model.MovieDetails
import com.example.movieapp.data.viewmodels.DetailsViewModel
import com.example.movieapp.ui.details.DetailsFragment
import com.example.movieapp.ui.favorite.repository.FavoriteRepository
import com.example.movieapp.ui.favorite.room.FavoriteDao
import com.example.movieapp.ui.favorite.room.FavoriteDatabase
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {
//LiveData ->data don't modified
// MutableLiveData ->data will modify later
var favoriteList: LiveData<ArrayList<MovieDetails>>?=null

    private val TAG = "FavoriteViewModel"
  // var movies= LiveData<ArrayList<Result>>()
    var movies= MutableLiveData<ArrayList<MovieDetails>?>()
    var fav =MutableLiveData<Boolean>()


fun insertMovie(context: Context,result:MovieDetails){

    FavoriteRepository.insertInDB(context,result)
}

fun deleteMovie(context: Context, movieId:Int){

    FavoriteRepository.deleteMoviesFromDB(context,movieId)
}

fun getMovies(context: Context) {
    
    //movies.postValue(FavoriteRepository.getMoviesFromDB(context)) --> if it mutable
    movies.postValue(FavoriteRepository.getMoviesFromDB(context)?.value as ArrayList<MovieDetails>?)

}

    fun isFav(movieId:Int){
        //if object ==null -->false  so fav =false
        //if object !=null -->true  so fav =true
        viewModelScope.launch(Dispatchers.IO){
            fav.postValue(FavoriteRepository.isFavorite(movieId)?.equals(null) == false)
        }

       // FavoriteRepository.isFavorite(movieId)
      //  FavoriteRepository.fav.postValue(FavoriteRepository.favoriteDatabase?.movieDao()?.isFavorite(movieId)?.equals(null)==false)
    }
}