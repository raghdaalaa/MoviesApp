package com.example.movieapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.view.WindowCompat
import com.example.movieapp.ui.MainActivity

class SplashScreen : AppCompatActivity() {
    private val handler = Handler(Looper.myLooper()!!)
    private val runnable = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT

        setContentView(R.layout.activity_splash_screen)
        /*   val intent=Intent(this,MainActivity::class.java)
           val t:Thread=object :Thread(){

               override fun run() {
                   try {
                       sleep(5000)
                       startActivity(intent)
                       finish()
                   }catch (e:InterruptedException){
                       e.printStackTrace()
                   }

               }
           }
           t.start()*/


    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(runnable, 2000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}