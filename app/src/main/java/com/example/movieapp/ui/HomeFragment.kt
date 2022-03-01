package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.tablayout.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(), OnNavigate {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[MovieListViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewpager
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle,this)


        binding.pager.adapter = adapter
        //   viewPager2.isUserInputEnabled=false         // if users can not scroll the ViewPager

        //tablayout
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Popular"
                }
                1 -> {
                    tab.text = "TopRated"
                }
                2 -> {
                    tab.text = "Favorite"
                }
            }
        }.attach()
    }

    override fun onNavigationSelected(action: Int, bundle: Bundle?) {
        findNavController().navigate(action, bundle)
    }
}

interface OnNavigate {
    fun onNavigationSelected(action: Int, bundle: Bundle?)
}









