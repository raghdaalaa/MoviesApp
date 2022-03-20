package com.example.movieapp.ui

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment():Fragment() {

    fun toast(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
}