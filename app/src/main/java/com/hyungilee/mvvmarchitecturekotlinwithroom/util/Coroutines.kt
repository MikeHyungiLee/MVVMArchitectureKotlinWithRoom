package com.hyungilee.mvvmarchitecturekotlinwithroom.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

object Coroutines {

    // parameter : coroutine function (suspend function)
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

}