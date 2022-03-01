package com.example.movieapp.ui.home.adapters

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener (layoutManager: LinearLayoutManager) :RecyclerView.OnScrollListener(){

    val layoutManager :LinearLayoutManager=layoutManager
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount=layoutManager.childCount
        val totalItemcount=layoutManager.itemCount
        val firstVisibleItemposition=layoutManager.findFirstVisibleItemPosition()

      //current < total -> load
        if (!isLoading() && !isLastPage()){
            if (visibleItemCount+ firstVisibleItemposition >=totalItemcount){
                loadMoreMovies()
            }
        }

    }

    abstract fun loadMoreMovies()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean


}