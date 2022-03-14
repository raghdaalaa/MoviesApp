package com.example.movieapp.ui.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentFavoriteBinding
import com.example.movieapp.ui.favorite.adapter.FavoriteAdapter


class FavoriteFragment : Fragment() {

private  var viewModel: FavoriteViewModel?=null
private lateinit var binding: FragmentFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[FavoriteViewModel::class.java]
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
        val adapterPopular = FavoriteAdapter()
        binding.favoriteRv.adapter = adapterPopular

        //observe on ViewModel to getAllFavMovies
    }


}