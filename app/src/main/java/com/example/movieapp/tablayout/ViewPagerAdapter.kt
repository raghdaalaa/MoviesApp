package com.example.movieapp.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.tablayout.favorite.ui.FavoriteFragment
import com.example.movieapp.ui.OnNavigate

class ViewPagerAdapter(fragmentManager: FragmentManager ,lifecycle: Lifecycle,val onNavigate: OnNavigate) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3  // number of fragments
    }

    override fun createFragment(position: Int): Fragment {

       return when(position){
           0->{
               PopularFragment(onNavigate)
           }
           1->{
               TopRatedFragment(onNavigate)
           }
           2->{
               FavoriteFragment(onNavigate)
           }
           else ->{
               Fragment()
           }
       }

    }


}