package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.FragmentVideoPlayerBinding
import com.example.movieapp.tablayout.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2: ViewPager2
  //  private lateinit var viewModel: MovieListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
//        val viewPager2=findViewById<ViewPager2>(R.id.pager)
//viewModel=ViewModelProvider(this)[MovieListViewModel::class.java]

        viewPager2=binding.pager
//        viewPager2.isUserInputEnabled=false         // if users can not scroll the ViewPager
        val adapter= ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager2.adapter=adapter
        val tabLayout=binding.tabLayout

        TabLayoutMediator(tabLayout,viewPager2){tab,position ->
            when(position){
                0-> {
                    tab.text="Popular"
                }
                1->{
                    tab.text="TopRated"
                }
                2->{
                    tab.text="Favorite"
                }
            }
        }.attach()
    }
    }
