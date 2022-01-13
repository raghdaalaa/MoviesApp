package com.example.moviesapp.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.api.model.Result
import com.example.moviesapp.data.base.BASE_IMAGE_URL
import coil.load
import com.example.movieapp.R


class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var mList: List<Result>? = null
    private var action: Int? = null
    var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the view_design that is used to hold list item

        val view=LayoutInflater.from(parent.context).inflate(R.layout.rv_item_design ,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie=mList?.get(position)
        holder.title?.text=movie?.title
        holder.rating?.text=movie?.getRating()

        if(movie?.posterPath !== ""){
            holder.poster?.load(BASE_IMAGE_URL + movie?.posterPath) {
crossfade(true)
                crossfade(500)
            }
        }


        holder.container?.setOnClickListener {
            val bundle= bundleOf("posterPath" to movie?.posterPath)
            bundle.putInt("id" ,movie?.id!!)
            it.findNavController().navigate(action!!,bundle,null,null)
        }
    }

    override fun getItemCount(): Int {
        if (mList !=null)
            return mList!!.size
        return 0
    }

    fun setMoviesData(mList:List<Result> ,action:Int){
        this.mList=mList
        this.action=action
        notifyDataSetChanged()
    }

    class ViewHolder(ItemView:View) :RecyclerView.ViewHolder(ItemView) {
        val container:CardView=itemView.findViewById(R.id.movieContainer)
  val poster :ImageView? =itemView.findViewById(R.id.poster_iv)
  val title :TextView? =itemView.findViewById(R.id.title_tv)
  val rating :TextView? =itemView.findViewById(R.id.rating_tv)
    }
}