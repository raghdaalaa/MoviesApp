package com.example.moviesapp.data.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.api.model.Result
import com.example.moviesapp.ui.home.HomeRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieListViewModel :ViewModel(){

private val repository =HomeRepository()
    var listPopular=MutableLiveData<List<Result>>()
    var listTopRated=MutableLiveData<List<Result>>()

    init {
         getMovieCatogery()
    }

    private fun getMovieCatogery(){
viewModelScope.launch(IO) {
 val list=repository.getPopular()
    withContext(Main){
        listPopular.postValue(list!!)
    }
}
        viewModelScope.launch(IO) {
            val list=repository.getTopRated()
            withContext(Main){
                listTopRated.postValue(list!!)
            }
        }

    }


}




//    private var networkingHelper = MovieClient.getInstance()
//
//   private  val _loadMovies = MutableLiveData<Result?>()
//    val loadMovies: LiveData<Result?> = _loadMovies
//
//    private val _isLoading = MutableLiveData<Boolean?>()
//    val isLoading: LiveData<Boolean?> = _isLoading
//
//    private val _errorMessage = MutableLiveData<String?>()
//    val errorMessage: LiveData<String?> = _errorMessage
//
//    private val disposables = CompositeDisposable()
//    private var page: Int = 1
//
//    fun getMovieList(categoryId: String) {
//        when (categoryId) {
//            "Popular" -> {
//                getPopular()
//            }
//            "Top Rated" -> {
//             //   getTopRated()
//            }
//        }
//    }
//
////    private fun getTopRated() {
////        _isLoading.postValue(true)
////
////        val disposable = networkingHelper.getMovieApi()
////            .getTopRatedMovies(apiKey,page)
////            .subscribeOn(Schedulers.io())
////            .observeOn(AndroidSchedulers.mainThread())
////            .subscribeWith(object : DisposableSingleObserver<Result>() {
////                override fun onSuccess(t: Result?) {
////                    _isLoading.postValue(false)
////                    t?.let {
////                        _loadMovies.postValue(it)
////                        page += 1
////                    }
////                }
////
////                override fun onError(e: Throwable?) {
////                    _isLoading.postValue(false)
////                    _errorMessage.postValue(e?.message)
////                }
////
////            })
////        disposables.add(disposable)
////    }
//
//
//     fun getPopular() {
//
//         val call: Call<Result> = networkingHelper.getMovieApi()
//             .getPopularMovies(apiKey,page)
//call.enqueue(object :Callback<Result>{
//    override fun onResponse(call: Call<Result>?, response: Response<Result>?) {
//if (response?.isSuccessful!!){
//    _loadMovies.postValue(response.body())
//    }
//}
//
//    override fun onFailure(call: Call<Result>, t: Throwable) {
//        call.cancel()
//         _errorMessage.postValue(t?.message)
//    }
//
//})
//
//    }
//
////    override fun onCleared() {
////        disposables.dispose()
////        disposables.clear()
////        super.onCleared()
////    }

