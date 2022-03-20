package com.example.movieapp.ui.favorite.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentFavoriteBinding
import com.example.movieapp.ui.favorite.adapter.FavoriteAdapter
import com.example.movieapp.ui.home.adapters.CustomAdapter


class FavoriteFragment : Fragment() ,CustomAdapter.OnItemClick {

private lateinit var favoriteViewModel: FavoriteViewModel
private lateinit var binding: FragmentFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteViewModel=ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.favoriteRv.layoutManager = lm
        val adapterFav=CustomAdapter(this)
        binding.favoriteRv.adapter = adapterFav
        context?.let { it1 -> favoriteViewModel.getMovies(it1) }

        //observe on ViewModel to getAllFavMovies
       favoriteViewModel.movies?.observe(viewLifecycleOwner){

           if (it != null) {
               adapterFav.appendMoreMovies(it)
           }
       }
    }

    override fun onItemClick(id: Int) {


    }

    override fun onItemLongClick(id: Int) {
        context?.let { favoriteViewModel.deleteMovie(it,id) }
    }


}