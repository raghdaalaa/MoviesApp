package com.example.movieapp.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.databinding.FragmentPopularBinding
import com.example.movieapp.ui.OnNavigate
import com.example.movieapp.ui.home.adapters.CustomAdapter
import com.example.movieapp.ui.home.adapters.PaginationScrollListener


class PopularFragment(val onNavigate: OnNavigate) : Fragment(), CustomAdapter.OnItemClick {

    private val TAG = "PopularFragment"
    private var homeViewModel: MovieListViewModel? = null
    private lateinit var binding: FragmentPopularBinding


    //pagination
    private var currentPage = 1
    var isLoading = false
    var isLastPage = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel =
            activity?.let { ViewModelProvider(it) }?.get(MovieListViewModel::class.java)
    //VM attatched to activity lifecycle

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isLoading = true
        if (homeViewModel?.listPopular?.value == null/*means no data loaded(in first)*/|| homeViewModel?.listPopular?.value?.page ?: 0 < 2)
            homeViewModel?.getPopularMovies(page = currentPage)
        val lm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.popularRv.layoutManager = lm
        val adapterPopular = CustomAdapter(this)
        binding.popularRv.adapter = adapterPopular

        homeViewModel?.listPopular?.observe(viewLifecycleOwner) {
            if (it!=null) {
                currentPage = it.page
                isLoading = false
                isLastPage = (it.page == it.totalPages)
            }
        }

        homeViewModel?.listOfMovies?.observe(viewLifecycleOwner) {
      //if there is data in (listOfMovies) it will appended in adapter
            //adapter always has data that has been loaded
            adapterPopular.clear()  // لان دايما اللي راجعلي الليست كلها
            if (it != null)
                adapterPopular.appendMoreMovies(it)
        }


// popular recyclerview pagination
        binding.popularRv.addOnScrollListener(object : PaginationScrollListener(lm) {
            override fun loadMoreMovies() {
                isLoading = true
                currentPage += 1
                homeViewModel?.getPopularMovies(currentPage)
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
        val bundle = Bundle()
        bundle.putInt("id", id)
        onNavigate.onNavigationSelected(R.id.action_homeFragment_to_detailsFragment5, bundle)

//        findNavController().navigate(R.id.action_popularFragment_to_detailsFragment5!!,bundle,null,null)
    }

    override fun onItemLongClick(id: Int) {
        TODO("Not yet implemented")
    }
}