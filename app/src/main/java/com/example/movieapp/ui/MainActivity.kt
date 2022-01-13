package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.tablayout.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=findViewById<ViewPager2>(R.id.pager)

        val adapter= ViewPagerAdapter(supportFragmentManager,lifecycle)

        viewPager2.adapter=adapter

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
