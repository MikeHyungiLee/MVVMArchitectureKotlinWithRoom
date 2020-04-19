package com.hyungilee.mvvmarchitecturekotlinwithroom.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// ProgressBar 보여주는 method
fun ProgressBar.show(){
    visibility = View.VISIBLE
}

// ProgressBar 숨겨주는 method
fun ProgressBar.hide(){
    visibility = View.GONE
}
