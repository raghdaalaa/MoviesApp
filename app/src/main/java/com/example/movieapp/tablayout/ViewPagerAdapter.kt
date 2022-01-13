package com.example.movieapp.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager ,lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3  // number of fragments
    }

    override fun createFragment(position: Int): Fragment {

       return when(position){
           0->{
               PopularFragment()
           }
           1->{
               TopRatedFragment()
           }
           2->{
               FavoriteFragment()
           }
           else ->{
               Fragment()
           }
       }

    }


}