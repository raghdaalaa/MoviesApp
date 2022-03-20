package com.example.movieapp.ui

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.tablayout.ViewPagerAdapter
import com.example.movieapp.ui.search.ui.SearchMovieViewModel
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment(), OnNavigate {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var searchViewModel: SearchMovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enable options menu in fragment
        setHasOptionsMenu(true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        searchViewModel = ViewModelProvider(this)[SearchMovieViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewpager
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle, this)

//        binding.includedToolbar.tvTitle
        binding.pager.adapter = adapter
        //   viewPager2.isUserInputEnabled=false         // if users can not scroll the ViewPager

        //tablayout
        binding.toolbarDesignId.searchIconTop.setOnClickListener {
            val bundle= Bundle()
            bundle.putInt("id" ,id)
            onNavigationSelected(R.id.action_homeFragment_to_searchMovieFragment,bundle)
        }
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Popular"
                }
                1 -> {
                    tab.text = "TopRated"
                }
            }
        }.attach()


        }


    // control communication between pages
    override fun onNavigationSelected(action: Int, bundle: Bundle?) {
        findNavController().navigate(action, bundle)
    }

    //options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.search_icon_top->{

                true
            }
            else ->super.onOptionsItemSelected(item)

        }

    }
}

interface OnNavigate {
    fun onNavigationSelected(action: Int, bundle: Bundle?)
}









