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
import com.example.movieapp.databinding.FragmentTopRatedBinding
import com.example.moviesapp.data.viewmodels.MovieListViewModel
import com.example.moviesapp.ui.home.adapters.CustomAdapter


class TopRatedFragment : Fragment() ,View.OnClickListener{

    private val TAG="TopRatedFragment"
    private lateinit var homeViewModel: MovieListViewModel
    private lateinit var binding: FragmentTopRatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel= ViewModelProvider(this)[MovieListViewModel::class.java]
        binding= FragmentTopRatedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topRatedRv.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val adapterTopRated= CustomAdapter()
        binding.topRatedRv.adapter=adapterTopRated

        homeViewModel.listTopRated.observe(viewLifecycleOwner){
            adapterTopRated.setMoviesData(it,R.id.action_topRatedFragment_to_detailsFragment5)
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}