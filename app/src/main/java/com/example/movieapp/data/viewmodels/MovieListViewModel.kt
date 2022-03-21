package com.example.movieapp.data.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.api.model.MovieDetails
import com.example.movieapp.data.api.model.MovieResponseModel
import com.example.movieapp.ui.home.HomeRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel() : ViewModel() {
    private val TAG = "MoviesViewModel"
    private val repository = HomeRepository()
    var listPopular = MutableLiveData<MovieResponseModel>()
    var listTopRated = MutableLiveData<MovieResponseModel>()
    var listOfMovies = MutableLiveData<ArrayList<MovieDetails>>()

//    // function to get movies from retrofit
//    public fun getMovieCatogery(page:Int) {
//        viewModelScope.launch(IO) {
//
//            val response = repository.getPopular(page)
//            withContext(Main) {
//                listPopular.postValue(response.body())
//            }
//        }
//        viewModelScope.launch(IO) {
//
//            val response = repository.getTopRated(page)
//            withContext(Main) {
//                listTopRated.postValue(response.body())
//            }
//        }
//    }
//
//}


    fun getPopularMovies(page: Int) {
        viewModelScope.launch(IO) {

            kotlin.runCatching {
                repository.getPopular(page)
            }.onSuccess {
                Log.d(TAG, "getMovies: ${it.code()}")
                if (it.isSuccessful) {
                    listPopular.postValue(it.body())
                    it.body()?.let {
                        if (it.page == 1) {   //update or refresh
                            withContext(Main) {
                                listOfMovies.value = ArrayList()    //clear listOfMovies
                            }
                        }
                        listOfMovies.value?.addAll(it.results)  // add value of listPopular(LiveData) -->getValue
                        // in listOfMovies(liveData that has all data)
                        //it.results -> value of listPopular
                        listOfMovies.postValue(listOfMovies.value)      // setValue
                        //postValue/setValue --> دي الميثود اللي بتسمع التغيير اللي حصل في الليست
                        //بتشتغل في الباج جراوند فمش محتاجين نعمل سوتش لل post--> ui
                        //set --> ui مش بتشتغل في الباج جراوند فمحتاجين نعمل سوتش لل
                    }
                } else
                    if (BuildConfig.DEBUG)
                    Log.d(TAG, "getMovies: " + it.code())
            }.onFailure {
                Log.d(TAG, "getMovies: ${it.message}")
                listPopular.postValue(null)
            }

        }

    }

    //topRated
    fun getTopRatedMovies(page: Int) {
        viewModelScope.launch(IO) {

            kotlin.runCatching {
                repository.getTopRated(page)
            }.onSuccess {
                Log.d(TAG, "getTopRatedMovies: ${it.code()}")
                if (it.isSuccessful) {
                    listTopRated.postValue(it.body())
                    it.body()?.let {
                        if (it.page == 1) {   //update or refresh
                            withContext(Main) {
                                listOfMovies.value = ArrayList()    //clear listOfMovies
                            }
                        }
                        listOfMovies.value?.addAll(it.results)  // add value of listPopular(LiveData) -->getValue
                        // in listOfMovies(liveData that has all data)
                        //it.results -> value of listPopular
                        listOfMovies.postValue(listOfMovies.value)      // setValue
                        //postValue/setValue --> دي الميثود اللي بتسمع التغيير اللي حصل في الليست
                        //بتشتغل في الباج جراوند فمش محتاجين نعمل سوتش لل post--> ui
                        //set --> ui مش بتشتغل في الباج جراوند فمحتاجين نعمل سوتش لل
                    }
                } else
                    Log.d(TAG, "getMovies: " + it.code())
            }.onFailure {
                Log.d(TAG, "getMovies: ${it.message}")
                listTopRated.postValue(null)
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

