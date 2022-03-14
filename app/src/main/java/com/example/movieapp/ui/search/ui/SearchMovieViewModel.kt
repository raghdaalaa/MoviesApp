package com.example.movieapp.ui.search.ui

import android.util.Log
import android.util.Range
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.model.MovieResponseModel
import com.example.movieapp.data.api.model.Result
import com.example.movieapp.data.api.model.SearchMoviesResponse
import com.example.movieapp.data.api.model.SearchResultsItem
import com.example.movieapp.ui.search.repository.SearchMoviesRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class SearchMovieViewModel : ViewModel() {

    private val TAG = "SearchMovieViewModel"
    private val repository = SearchMoviesRepository()
    val listSearchMovies = MutableLiveData<SearchMoviesResponse>()

    fun getSearchResult(quary: String) {
        viewModelScope.launch(IO) {
            val response = repository.getSearch(quary)
            withContext(Main) {
                if (response.code() in 200..299) {
                    listSearchMovies.postValue(response.body())
                } else if (response.code() in 400..499) {
                    Log.d(TAG,"Response error massage : "+response.errorBody()?.string())

                }
                }

            }
        }
    }
