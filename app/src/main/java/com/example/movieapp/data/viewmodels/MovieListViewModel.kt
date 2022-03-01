package com.example.movieapp.data.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.model.MovieResponseModel
import com.example.movieapp.data.api.model.Result
import com.example.movieapp.ui.home.HomeRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel() :ViewModel() {
    private val TAG = "MoviesViewModel"
    private val repository = HomeRepository()
    var listPopular = MutableLiveData<MovieResponseModel>()

    var listTopRated = MutableLiveData<MovieResponseModel>()

    // function to get movies from retrofit
    public fun getMovieCatogery(page:Int) {
        viewModelScope.launch(IO) {
            val response = repository.getPopular(page)
            withContext(Main) {
                listPopular.postValue(response.body())
            }
        }
        viewModelScope.launch(IO) {
            val response = repository.getTopRated(page)
            withContext(Main) {
                listTopRated.postValue(response.body())
            }
        }
    }

}

//--------------------------------Callbacks-------------------------------------------------
// function to get movies from retrofit
//public fun getMovies(){
//
//    val list=repository.getPopular()
//    list.enqueue(object : Callback<List<PopularResult>> {
//        override fun onResponse(
//            call: Call<List<PopularResult>>,
//            response: Response<List<PopularResult>>
//        ) {
//            if (response.isSuccessful) // check success of response
//                listPopular.postValue(response.body())
//            else if (response.code() in 400..499){
//                Log.d(TAG, "onResponse: "+response.errorBody()?.string())
////                    val errorJSon=JSONObject() // equal GsonConvertFactory
//            }
//        }
//
//        override fun onFailure(call: Call<List<PopularResult>>, t: Throwable) {
//
//        }
//
//    })
//
//}

