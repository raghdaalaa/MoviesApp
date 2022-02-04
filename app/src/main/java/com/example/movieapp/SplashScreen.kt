package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.ui.MainActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val intent=Intent(this,MainActivity::class.java)
        val t:Thread=object :Thread(){

            override fun run() {
                try {
                    sleep(2000)
                    startActivity(intent)
                    finish()
                }catch (e:InterruptedException){
                    e.printStackTrace()
                }

            }
        }
        t.start()
    }
}