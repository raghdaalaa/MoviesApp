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

       val lm= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.topRatedRv.layoutManager=lm
        val adapterTopRated= CustomAdapter(this)
        binding.topRatedRv.adapter=adapterTopRated

        homeViewModel.listTopRated.observe(viewLifecycleOwner){

            currentPage=it.page
            isLoading=false
            isLastPage=(it.page == it.totalPages)
            if (currentPage ==1){
                adapterTopRated.clear()
            }
            adapterTopRated.appendMoreMovies(it.results)
        }


        //topRated rv
        binding.topRatedRv.addOnScrollListener(object :PaginationScrollListener(lm){
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
//findNavController().navigate(R.id.action_topRatedFragment_to_detailsFragment5!!,bundle,null,null)
    }

//    override fun onClick(p0: View?) {
//        when(p0?.id){
//            R.id.movieContainer -> bundleOf("position" to "TopRated")
//        }
//        p0?.findNavController()?.navigate(R.id.action_topRatedFragment_to_detailsFragment5)
//    }
    }


