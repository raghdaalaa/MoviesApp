package com.example.movieapp.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.TextKeyListener.clear
import android.view.Menu
import android.view.MenuInflater
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.tablayout.ViewPagerAdapter
import com.example.movieapp.toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // to make status bar transparent
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        window.statusBarColor=Color.TRANSPARENT



        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.bottom_nav)
         navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            // الميثود دي علشان الفراجمنت تسمع التغيير اللي حصل
//            destination.id==R.id.homeFragment
           navView.isVisible=(destination.id==R.id.homeFragment)
        }

    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id==R.id.searchMovieFragment){
           val intent=Intent("back_action")
            sendBroadcast(intent)

        }else
        super.onBackPressed()
    }
}
