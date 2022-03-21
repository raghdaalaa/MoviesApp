package com.example.movieapp.ui.home.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.base.BASE_IMAGE_URL
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.movieapp.R
import com.example.movieapp.data.api.model.MovieDetails
import com.example.movieapp.databinding.RvItemDesignBinding


class CustomAdapter(val listener: OnItemClick) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val TAG = "CustomAdapter"
    private var mList: ArrayList<MovieDetails>? = ArrayList()
    var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the view_design that is used to hold list item

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d(TAG, "onBindViewHolder: ")
//        val movie=mList?.get(position)

        holder.apply {
            mList?.get(position)?.let { movie ->

                title.text = movie.title
                rating.text = movie.voteAverage.toString()
                release_data.text = movie.releaseDate
                poster.load(BASE_IMAGE_URL + movie.posterPath) {
                    crossfade(true)
                    crossfade(500)
                    transformations(RoundedCornersTransformation(20f))
                }
            }


        }

    }

    override fun getItemCount(): Int {
        if (mList != null)
            return mList!!.size
        return 0
    }

    fun appendMoreMovies(mList: ArrayList<MovieDetails>) {
        this.mList?.addAll(mList)
        notifyDataSetChanged()
    }


    fun clear() {
        mList?.clear()
        notifyDataSetChanged()
    }
//    fun setTopRated(mList:List<MovieDetails>,action: Int){
//        this.mList=mList
//        this.action=action
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        private val binding = RvItemDesignBinding.bind(itemView)
        val movieContainer = binding.movieContainer
        val poster = binding.posterIv
        val title = binding.titleTv
        val rating = binding.ratingTv
        val release_data = binding.movieReleaseDate


        init {

            movieContainer?.setOnClickListener {
                if (bindingAdapterPosition!=-1)
                mList?.get(bindingAdapterPosition)?.id?.let { it1 -> listener.onItemClick(it1) }
            }

            movieContainer?.setOnLongClickListener {
                if (bindingAdapterPosition!=-1)
                mList?.get(bindingAdapterPosition)?.id?.let { it1 -> listener.onItemLongClick(it1) }
                return@setOnLongClickListener true
            }

        }
    }

    interface OnItemClick {
        fun onItemClick(id: Int)
        fun onItemLongClick(id: Int)

    }
}