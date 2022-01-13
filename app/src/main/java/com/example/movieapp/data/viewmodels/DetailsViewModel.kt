package com.example.moviesapp.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.api.MovieClient
import com.example.moviesapp.data.api.model.MovieDetails
import com.example.moviesapp.data.api.model.ProductionCountry
import com.example.moviesapp.data.api.videos.MovieTrailerResponse
import com.example.moviesapp.data.api.videos.ResultTrailerResponse
import com.example.moviesapp.data.base.apiKey
import com.example.moviesapp.ui.details.repository.DetailsRepository
import io.reactivex.observers.DisposableSingleObserver
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.annotations.NonNull
//import io.reactivex.rxjava3.disposables.Disposable
//import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailsViewModel :ViewModel() {

    private val TAG = "AllMoviesViewModel"
    private val moviesDetailsRepository= DetailsRepository()

    var trailerId = MutableLiveData<ResultTrailerResponse?>()
    var movieDetails = MutableLiveData<MovieDetails?>()
    var movieCountry = MutableLiveData<ProductionCountry?>()


    fun getTrailerMovie(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val trailer = moviesDetailsRepository.getTrailer(id)

            withContext(Main) {
                trailerId.postValue(trailer)
            }
        }
    }
    fun getMoviesDetails(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = moviesDetailsRepository.getMoviesDetails(id)
            withContext(Main) {
                movieDetails.postValue(list)
            }
        }
    }

    fun getMovieCountry(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val country = moviesDetailsRepository.getMoviesCountry(id)

            withContext(Main) {
                movieCountry.postValue(country)
            }
        }
    }

}
