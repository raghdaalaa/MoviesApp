package com.example.movieapp.ui.search.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.model.MovieResponseModel
import com.example.movieapp.data.api.model.SearchMoviesResponse
import com.example.movieapp.ui.search.repository.SearchMoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMovieViewModel :ViewModel() {

   private val repository=SearchMoviesRepository()
    val listSearchMovies= MutableLiveData<SearchMoviesResponse>()

    fun getSearchMovies(quary:String){
        viewModelScope.launch(IO) {
            val response=repository.getSearch(quary)
            withContext(Main){
listSearchMovies.postValue(response.body())
            }
        }
    }
}