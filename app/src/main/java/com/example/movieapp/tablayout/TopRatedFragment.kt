package com.example.movieapp.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentTopRatedBinding
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.ui.OnNavigate
import com.example.movieapp.ui.home.adapters.CustomAdapter
import com.example.movieapp.ui.home.adapters.PaginationScrollListener


class TopRatedFragment(val onNavigate: OnNavigate) : Fragment(), CustomAdapter.OnItemClick {

    private val TAG="TopRatedFragment"
    private lateinit var homeViewModel: MovieListViewModel
    private lateinit var binding: FragmentTopRatedBinding

    //pagination
    var currentPage=1
    var isLoading=false
    var isLastPage=false

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

        isLoading = true
        if (homeViewModel?.listTopRated?.value == null/*means no data loaded(in first)*/|| homeViewModel?.listTopRated?.value?.page ?: 0 < 2)
            homeViewModel?.getTopRatedMovies(page = currentPage)
        val lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.topRatedRv.layoutManager = lm
        val adapterTopRated = CustomAdapter(this)
        binding.topRatedRv.adapter = adapterTopRated

        homeViewModel?.listTopRated?.observe(viewLifecycleOwner) {
            if (it!=null) {
                currentPage = it.page
                isLoading = false
                isLastPage = (it.page == it.totalPages)
            }
        }

        homeViewModel?.listOfMovies?.observe(viewLifecycleOwner) {
            //if there is data in (listOfMovies) it will appended in adapter
            //adapter always has data that has been loaded
            adapterTopRated.clear()  // لان دايما اللي راجعلي الليست كلها
            if (it != null)
                adapterTopRated.appendMoreMovies(it)
        }


// popular recyclerview pagination
        binding.topRatedRv.addOnScrollListener(object : PaginationScrollListener(lm) {
            override fun loadMoreMovies() {
                isLoading = true
                currentPage += 1
                homeViewModel?.getTopRatedMovies(currentPage)
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }


        })
    }

    override fun onItemClick(id: Int) {
        val bundle= Bundle()
        bundle.putInt("id" ,id)
        onNavigate.onNavigationSelected(R.id.action_homeFragment_to_detailsFragment5,bundle)
//findNavController().navigate(R.id.action_topRatedFragment_to_detailsFragment5!!,bundle,null,null)
    }

    override fun onItemLongClick(id: Int) {
        TODO("Not yet implemented")
    }

    }


