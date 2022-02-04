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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.api.model.Result
import com.example.movieapp.data.base.BASE_IMAGE_URL
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.movieapp.R
import com.example.movieapp.databinding.RvItemDesignBinding


class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val TAG = "CustomAdapter"
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
        holder.title.text=movie?.title
        holder.rating.text=movie?.voteAverage.toString()

            holder.poster?.load(BASE_IMAGE_URL + movie?.posterPath) {
crossfade(true)
                crossfade(500)
                transformations(RoundedCornersTransformation(20f))
            }



        holder.movieContainer?.setOnClickListener {
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

    fun setPopular(mList:List<Result>,action: Int){
        this.mList=mList
        this.action=action
        notifyDataSetChanged()
    }

//    fun setTopRated(mList:List<Result>,action: Int){
//        this.mList=mList
//        this.action=action
//        notifyDataSetChanged()
//    }

    class ViewHolder(ItemView:View) :RecyclerView.ViewHolder(ItemView) {
        private val binding=RvItemDesignBinding.bind(itemView)
        val movieContainer=binding.movieContainer
        val poster=binding.posterIv
        val title=binding.titleTv
        val rating=binding.ratingTv

    }
}