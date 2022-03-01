package com.example.movieapp.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.tablayout.ViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        window.statusBarColor=Color.TRANSPARENT
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.bottom_nav)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

    }

}
