package com.example.movieapp.data.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.model.MovieDetails
import com.example.movieapp.data.api.model.ProductionCompany
import com.example.movieapp.data.api.videos.ResultTrailerResponse
import com.example.movieapp.ui.details.repository.DetailsRepository
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
//import io.reactivex.rxjava3.disposables.Disposable
//import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailsViewModel :ViewModel() {

    private val TAG = "AllMoviesViewModel"
    private val moviesDetailsRepository= DetailsRepository()

    var trailerId = MutableLiveData<ResultTrailerResponse?>()
    var movieDetails = MutableLiveData<MovieDetails?>()
    var movieCountry = MutableLiveData<ProductionCompany?>()


    fun getTrailerMovie(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                moviesDetailsRepository.getTrailer(id)
            }.onFailure {
                Log.e(TAG, "getTrailerMovie: ",it )
            }.onSuccess {
                Log.d(TAG, "getTrailerMovie: "+it.code())
                withContext(Main) {
                    trailerId.postValue(it.body()?.resultTrailerResponses?.get(0))
                }
            }


        }
    }
    fun getMoviesDetails(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = moviesDetailsRepository.getMoviesDetails(id)
//            withContext(Main) {
                movieDetails.postValue(list)
//            }
        }
    }

//    fun getMovieCountry(name:String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val country = moviesDetailsRepository.getMoviesCountry(name)
//
//            withContext(Main) {
//                movieCountry.postValue(country)
//            }
//        }
//    }

}
