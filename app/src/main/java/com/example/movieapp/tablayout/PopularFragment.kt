package com.example.movieapp.tablayout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentPopularBinding
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.ui.home.adapters.CustomAdapter


class PopularFragment : Fragment() {

    private val TAG = "PopularFragment"
    private lateinit var homeViewModel: MovieListViewModel
    private lateinit var binding: FragmentPopularBinding
    var isLoading = false
    private lateinit var type: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        type = arguments?.get("type").toString()

        //  activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) // to make status bar transparent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.popularRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapterPopular = CustomAdapter()
        binding.popularRv.adapter = adapterPopular
        homeViewModel.listPopular.observe(viewLifecycleOwner){
            adapterPopular.setPopular(it,R.id.action_popularFragment_to_detailsFragment5)

        }

// recyclerview pagination
        binding.popularRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d(TAG, "OnScrollListener: Called")
                if (!recyclerView.canScrollVertically(1)) {// 1 for down
                    if (!isLoading) {
                        when (type) {
                            "popular" -> {
                                homeViewModel.listPopular
                                Log.d(TAG, "raghda: Called")  // for test
                            }
                            "topRated" -> {
                                homeViewModel.listTopRated
                            }
                        }

                    }
                }
            }
        })
    }

//    override fun onClick(p0: View?) {
//        when (p0?.id) {
//            R.id.movieContainer -> bundleOf("type" to "POPULAR")
//        }
//        p0?.findNavController()?.navigate(R.id.action_popularFragment_to_detailsFragment5)
//    }
}