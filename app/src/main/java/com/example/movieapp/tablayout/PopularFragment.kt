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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentPopularBinding
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.ui.OnNavigate
import com.example.movieapp.ui.home.adapters.CustomAdapter
import com.example.movieapp.ui.home.adapters.PaginationScrollListener


class PopularFragment(val onNavigate: OnNavigate) : Fragment(), CustomAdapter.OnItemClick {

    private val TAG = "PopularFragment"
    private lateinit var homeViewModel: MovieListViewModel
    private lateinit var binding: FragmentPopularBinding


    //pagination
    private var currentPage=1
    var isLoading = false
    var isLastPage=false



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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isLoading=true
       homeViewModel.getMovieCatogery(page = currentPage)
       val lm= LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.popularRv.layoutManager=lm
        val adapterPopular = CustomAdapter(this)
        binding.popularRv.adapter = adapterPopular

        homeViewModel.listPopular.observe(viewLifecycleOwner){
            currentPage=it.page
            isLoading=false
            isLastPage=(it.page == it.totalPages)

            if (currentPage ==1){
                adapterPopular.clear()
            }

            adapterPopular.appendMoreMovies(it.results)


        }


// popular recyclerview pagination
        binding.popularRv.addOnScrollListener(object :PaginationScrollListener(lm) {
            override fun loadMoreMovies() {
                isLoading=true
                currentPage +=1
               homeViewModel.getMovieCatogery(currentPage)
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

//        findNavController().navigate(R.id.action_popularFragment_to_detailsFragment5!!,bundle,null,null)
    }

//    override fun onClick(p0: View?) {
//        when (p0?.id) {
//            R.id.movieContainer -> bundleOf("type" to "POPULAR")
//        }
//        p0?.findNavController()?.navigate(R.id.action_popularFragment_to_detailsFragment5)
//    }
}