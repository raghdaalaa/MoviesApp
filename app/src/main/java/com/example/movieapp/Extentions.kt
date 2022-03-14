package com.example.movieapp

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun String.toast(context: Context){
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun EditText.isInputValid()= this.text.length>0
