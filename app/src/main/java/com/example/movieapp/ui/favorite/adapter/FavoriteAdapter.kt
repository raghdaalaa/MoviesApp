package com.example.movieapp.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.movieapp.R
import com.example.movieapp.data.api.model.MovieDetails
import com.example.movieapp.data.base.BASE_IMAGE_URL
import com.example.movieapp.databinding.FavoriteItemBinding
import com.example.movieapp.ui.OnNavigate
import com.example.movieapp.ui.home.adapters.CustomAdapter

class FavoriteAdapter(): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewholder>(){

    private var favList:ArrayList<MovieDetails>?=ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewholder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.favorite_item,parent,false)
        return FavoriteViewholder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewholder, position: Int) {
        val favMovies=favList?.get(position)
        holder.title.text=favMovies?.title
        holder.poster?.load(BASE_IMAGE_URL +favMovies?.posterPath){
            crossfade(true)
            crossfade(500)
            transformations(RoundedCornersTransformation(20f))
        }
    }

    override fun getItemCount(): Int {
       if (favList != null)
           return favList!!.size
       return 0
    }

    fun appendMoreMovies(mList: ArrayList<MovieDetails>) {
        this.favList?.addAll(mList)
        notifyDataSetChanged()
    }


    class FavoriteViewholder(itemView:View): RecyclerView.ViewHolder(itemView) {
        private val binding=FavoriteItemBinding.bind(itemView)
        val poster=binding.favPoster
        val title=binding.favTitle

    }


}