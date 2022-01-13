package com.example.movieapp.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentPopularBinding
import com.example.moviesapp.data.viewmodels.MovieListViewModel
import com.example.moviesapp.ui.home.adapters.CustomAdapter


class PopularFragment : Fragment() ,View.OnClickListener{

    private val TAG="HomeFragment"
    private lateinit var homeViewModel: MovieListViewModel
    private lateinit var binding: FragmentPopularBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel= ViewModelProvider(this)[MovieListViewModel::class.java]
        binding= FragmentPopularBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) // to make status bar transparent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val helper=LinearSnapHelper() //if we want the item to snap in the middle of the screen
//        // when scrolling, one item at a time
//        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.popularRv.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val adapterPopular= CustomAdapter()
        binding.popularRv.adapter=adapterPopular


        homeViewModel.listPopular.observe(viewLifecycleOwner){
            adapterPopular.setMoviesData(it,R.id.action_popularFragment_to_detailsFragment5)
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}